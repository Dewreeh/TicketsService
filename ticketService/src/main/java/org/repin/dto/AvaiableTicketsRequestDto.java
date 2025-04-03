package org.repin.dto;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Data
public class AvaiableTicketsRequestDto {
    String departure;
    String destination;
    String carrier;
    LocalDateTime dateTime;
    int page;
    int pageSize;
}
