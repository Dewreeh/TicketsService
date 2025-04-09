package org.repin.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.repin.dto.AuthRequest;
import org.repin.dto.AuthResponse;
import org.repin.dto.RefreshRequest;
import org.repin.model.User;
import org.repin.repository.UserRepository;
import org.repin.service.security.CustomUserDetailsService;
import org.repin.service.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;
    private final UserRepository userRepository;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          JwtService jwtService,
                          CustomUserDetailsService userDetailsService,
                          UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
    }

    @Operation(description = "Аутентификация пользователя")
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(),
                            authRequest.getPassword()
                    )
            );
            User user = userRepository.findByUsername(authentication.getName()).orElseThrow();

            String token = jwtService.generateToken(authentication);
            String refreshToken = jwtService.generateRefreshToken(authentication);

            return ResponseEntity.ok(new AuthResponse(token, refreshToken, user.getId()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Operation(description = "Обновление токена по рефреш токену")
    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@Valid @RequestBody RefreshRequest refreshRequest) {
            String refresh = refreshRequest.getRefreshToken();

            String username = jwtService.extractUsername(refresh);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if(!jwtService.isTokenValid(refresh, userDetails)){
                throw new BadCredentialsException("Невалидный рефреш токен!");
            }

            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            String newToken = jwtService.generateToken(authentication);

            return ResponseEntity.ok(new AuthResponse(newToken, refresh, jwtService.extractUserId(newToken)));
    }
}