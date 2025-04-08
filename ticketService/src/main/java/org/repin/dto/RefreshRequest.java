package org.repin.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RefreshRequest{
    @NotBlank(message = "Пустой рефреш токен")
    String refreshToken;

}