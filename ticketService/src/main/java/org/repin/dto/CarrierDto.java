package org.repin.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CarrierDto {
    @NotBlank(message = "название обязательно")
    private String name;

    @NotBlank(message = "Телефон обязателен")
    @Size(min = 10, max = 10, message = "невалидная длина номера")
    private String phone;
}