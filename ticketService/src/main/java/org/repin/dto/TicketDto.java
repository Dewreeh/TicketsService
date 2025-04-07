package org.repin.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.*;

@Data
public class TicketDto {

    @NotNull(message = "ID маршрута обязательно для заполнения")
    @Positive(message = "ID маршрута должен быть положительным числом")
    private Long routeId;

    @NotNull(message = "Дата и время отправления обязательны для заполнения")
    @Future(message = "Дата отправления должна быть в будущем")
    private LocalDateTime dateTime;

    @NotNull(message = "Номер места обязателен для заполнения")
    @Min(value = 1, message = "Номер места должен быть не менее 1")
    @Max(value = 500, message = "Номер места должен быть не более 500")
    private Integer seatNumber;

    @NotNull(message = "Цена обязательна для заполнения")
    @DecimalMin(value = "0.01", message = "Цена должна быть больше 0")
    @Digits(integer = 6, fraction = 2, message = "Некорректный формат цены")
    private BigDecimal price;
}
