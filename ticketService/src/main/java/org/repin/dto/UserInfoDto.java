package org.repin.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserInfoDto {
    @NotBlank(message = "Логин не должен быть пустым")
    String username;

    @NotBlank(message = "Имя не должно быть пустым")
    String name;

    @NotBlank(message = "Пароль не должен быть пустым")
    @Size(min = 5, message = "Минимальная длина пароля 5 символов")
    String password;
}
