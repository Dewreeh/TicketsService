package org.repin.controller;

import org.repin.dto.AvaiableTicketsRequestDto;
import org.repin.model.Ticket;
import org.repin.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    private final TicketRepository ticketRepository;

    @Autowired
    public TicketController(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @GetMapping("/get")
    public ResponseEntity<Object> getAvailableTickets(
            @RequestParam(name = "departure", required = false) String departure,
            @RequestParam(name = "destination", required = false) String destination,
            @RequestParam(name = "carrier", required = false) String carrier,
            @RequestParam(name = "dateTime", required = false) LocalDateTime dateTime,
            @RequestParam(name = "page", defaultValue = "0") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  int page,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {

        List<Ticket> tickets = ticketRepository.findAvailableTickets(departure, destination, carrier, dateTime, page, pageSize);
        System.out.println(tickets);
        return ResponseEntity.ok(tickets);
    }
}
