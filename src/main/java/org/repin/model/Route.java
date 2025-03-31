package org.repin.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Route {
    private Long id;

    private String departurePoint;

    private String destinationPoint;

    private Carrier carrier;

    private Integer durationMinutes;
}