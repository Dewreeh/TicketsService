package org.repin.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.repin.dto.UserInfoDto;
import org.repin.service.UserService;
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

    private final UserService userService;

    @Autowired
    UserController(UserService userService){
        this.userService = userService;
    }

    @Operation(description = "Регистрирует пользователя, возвращает ошибку если username занят")
    @PostMapping("/register")
    ResponseEntity<String> registerUser(@Valid @RequestBody UserInfoDto UserInfodto) throws Exception {
        userService.saveUser(UserInfodto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Пользователь добавлен!");
    }

}
