package org.repin.dto;

import jakarta.validation.constraints.*;
import lombok.Data;


@Data
public class RouteDto {
    @NotBlank(message = "Пункт отправления обязателен")
    private String departurePoint;

    @NotBlank(message = "Пункт назначения обязателен")
    private String destinationPoint;

    @NotNull(message = "ID перевозчика обязательно")
    private Long carrierId;

    @NotNull(message = "Длительность обязательна")
    private Integer durationMinutes;
}