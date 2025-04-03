package org.repin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RouteDto {
    private Long id;
    private String departurePoint;
    private String destinationPoint;
    private CarrierDto carrier;
    private Integer durationMinutes;
}