package org.repin.controller;

import org.repin.model.Ticket;
import org.repin.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;


    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/get")
    public ResponseEntity<Object> getAvailableTickets(
            @RequestParam(name = "departure", required = false) String departure,
            @RequestParam(name = "destination", required = false) String destination,
            @RequestParam(name = "carrier", required = false) String carrier,
            @RequestParam(name = "dateTime", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime,
            @RequestParam(name = "page", defaultValue = "0")  int page,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {

        List<Ticket> tickets = ticketService.findAvailableTickets(departure, destination, carrier, dateTime, pageSize, page);

        return ResponseEntity.ok(tickets);
    }

    @PostMapping("/buy")
    public ResponseEntity<Object> buyTicket(@RequestParam("ticketId") Long ticketId,
                                            @RequestParam("userId") Long userId,
                                            @RequestHeader("Authorization") String authHeader) throws AccessDeniedException {

        return ticketService.buyTicket(ticketId, userId, authHeader);
    }

    @GetMapping("/get_my")
    public ResponseEntity<Object> getUserTickets(@RequestParam("userId") Long userId,
                                                 @RequestHeader("Authorization") String authHeader) throws AccessDeniedException {

        return ticketService.findUserTickets(userId, authHeader);
    }
}
