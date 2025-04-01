package org.repin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    private Long id;

    private Long routeId;

    private LocalDateTime dateTime;

    private Integer seatNumber;

    private BigDecimal price;

}