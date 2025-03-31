package org.repin.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserInfoDto {
    @NotBlank
    String login;

    @NotBlank
    String name;

    @NotBlank
    String password;
}
