package org.repin.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.repin.dto.CarrierDto;
import org.repin.dto.RouteDto;
import org.repin.dto.TicketDto;
import org.repin.model.Carrier;
import org.repin.model.Route;
import org.repin.model.Ticket;
import org.repin.service.CarrierService;
import org.repin.service.RouteService;
import org.repin.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {

    private final TicketService ticketService;
    private final RouteService routeService;
    private final CarrierService carrierService;

    @Autowired
    public AdminController(TicketService ticketService,
                           RouteService routeService,
                           CarrierService carrierService) {
        this.ticketService = ticketService;
        this.routeService = routeService;
        this.carrierService = carrierService;
    }


    @Operation(description = "Добавление билета")
    @PostMapping("/ticket/add")
    public ResponseEntity<Object> addTicket(@RequestBody @Valid TicketDto ticketDto) {

        return ticketService.addTicket(ticketDto);
    }

    @Operation(description = "Обновление билета")
    @PutMapping("/ticket/update")
    public ResponseEntity<Void> updateTicket(@RequestBody @Valid Ticket ticket) {
        ticketService.updateTicket(ticket);
        return ResponseEntity.ok().build();
    }

    @Operation(description = "Удаление билета")
    @DeleteMapping("/ticket/delete")
    public ResponseEntity<Void> deleteTicket(@RequestParam("id") Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.ok().build();
    }


    @Operation(description = "Добавление маршрута")
    @PostMapping("/route/add")
    public ResponseEntity<Void> addRoute(@RequestBody @Valid RouteDto routeDto) {
        routeService.addRoute(routeDto);
        return ResponseEntity.ok().build();
    }

    @Operation(description = "Обновление маршрута")
    @PutMapping("/route/update")
    public ResponseEntity<Void> updateRoute(@RequestBody @Valid Route route) {
        routeService.updateRoute(route);
        return ResponseEntity.ok().build();
    }

    @Operation(description = "Удаление маршрута")
    @DeleteMapping("/route/delete")
    public ResponseEntity<Void> deleteRoute(@RequestParam("id") Long id) {
        routeService.deleteRoute(id);
        return ResponseEntity.ok().build();
    }


    @Operation(description = "Добавление перевозчика")
    @PostMapping("/carrier/add")
    public ResponseEntity<Void> addCarrier(@RequestBody @Valid CarrierDto carrierDto) {
        carrierService.addCarrier(carrierDto);
        return ResponseEntity.ok().build();
    }

    @Operation(description = "Обновление перевозчика")
    @PutMapping("/carrier/update")
    public ResponseEntity<Void> updateCarrier(@RequestBody @Valid Carrier carrier) {
        carrierService.updateCarrier(carrier);
        return ResponseEntity.ok().build();
    }

    @Operation(description = "Удаление перевозчика")
    @DeleteMapping("/carrier/delete")
    public ResponseEntity<Void> deleteCarrier(@RequestParam("id")  Long id) {
        carrierService.deleteCarrier(id);
        return ResponseEntity.ok().build();
    }
}