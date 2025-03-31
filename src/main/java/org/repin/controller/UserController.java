package org.repin.controller;

import jakarta.validation.Valid;
import org.repin.dto.UserInfoDto;
import org.repin.model.User;
import org.repin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @PostMapping("/register")
    ResponseEntity<Object> registerUser(@Valid @RequestBody UserInfoDto dto) throws Exception {
        User user = User
                .builder()
                .login(dto.getLogin())
                .fullName(dto.getName())
                .password(dto.getPassword())
                .build();

        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/find")
    ResponseEntity<Object> findByLogin(@Valid @RequestBody UserInfoDto dto) throws Exception {
        User user = User
                .builder()
                .login(dto.getLogin())
                .fullName(dto.getName())
                .password(dto.getPassword())
                .build();

        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
