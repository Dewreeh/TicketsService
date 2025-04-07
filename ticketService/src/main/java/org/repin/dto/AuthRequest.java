package org.repin.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequest{

    @NotBlank(message = "Вы не ввели логин")
    String username;
    @NotBlank(message = "Вы не ввели пароль")
    String password;

}
