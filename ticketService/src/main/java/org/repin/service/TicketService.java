package org.repin.service;

import org.repin.dto.ErrorResponse;
import org.repin.dto.TicketDto;
import org.repin.model.Ticket;
import org.repin.repository.RouteRepository;
import org.repin.repository.TicketRepository;
import org.repin.repository.UserRepository;
import org.repin.service.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final RouteRepository routeRepository;
    private final RedisTemplate<String, Ticket> redisTemplate;
    private final KafkaProducerService kafkaProducerService;
    private final JwtService jwtService;

    @Autowired
    TicketService(TicketRepository ticketRepository,
                  UserRepository userRepository,
                  RedisTemplate<String, Ticket> redisTemplate,
                  KafkaProducerService kafkaProducerService,
                  RouteRepository routeRepository,
                  JwtService jwtService) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.redisTemplate = redisTemplate;
        this.kafkaProducerService = kafkaProducerService;
        this.routeRepository = routeRepository;
        this.jwtService = jwtService;
    }

    public List<Ticket> findAvailableTickets(String departure, String destination, String carrier, LocalDateTime dateTime, int limit, int offset){
        return ticketRepository.findAvailableTickets(departure, destination, carrier, dateTime, limit, offset);
    }

    public ResponseEntity<Object> findUserTickets(Long userId, String authHeader) throws AccessDeniedException {


        if(userRepository.find(userId).isEmpty()){
            return ResponseEntity.badRequest().body(new ErrorResponse("Пользователь не найден"));
        }

        jwtService.validateUserRights(authHeader, userId);

        List<Ticket> tickets = redisTemplate.opsForList().range(String.valueOf(userId), 0, -1);

        if(!tickets.isEmpty()){
            return ResponseEntity.ok(tickets);
        }

        tickets = ticketRepository.findUserTickets(userId);

        if(!tickets.isEmpty()) {
            redisTemplate.opsForList().rightPushAll(String.valueOf(userId), tickets);
        }

        return ResponseEntity.ok(tickets);
    }
    @Transactional
    public ResponseEntity<Object> buyTicket(Long ticketId, Long userId, String authHeader) throws AccessDeniedException {
        if(userRepository.find(userId).isEmpty()){
            return ResponseEntity.badRequest().body(new ErrorResponse("Пользователь не найден"));
        }

        if(ticketRepository.find(ticketId).isEmpty()){
            return ResponseEntity.badRequest().body(new ErrorResponse("Билет не найден"));
        }

        if(!ticketRepository.checkAvailability(ticketId)){
            return ResponseEntity.badRequest().body(new ErrorResponse("Билет уже куплен"));
        }

        jwtService.validateUserRights(authHeader, userId);

        ticketRepository.markAsSold(ticketId, userId);

        Ticket ticket = ticketRepository.find(ticketId).get(); //для сохранения в редис и отправки в кафку + мы ранее уже проверили наличие билета по id в таблице купленных, значит тут точно не будет null

        redisTemplate.opsForList().rightPush(String.valueOf(userId), ticket);

        kafkaProducerService.sendTicketToKafka(ticket);

        return ResponseEntity.ok("Билет приобретён");
    }

    public ResponseEntity<Object> addTicket(TicketDto ticketDto) {

        if(routeRepository.find(ticketDto.getRouteId()).isEmpty()){
            return ResponseEntity.badRequest().body(new ErrorResponse("Маршрут в билете не существует"));
        }


        Ticket ticket = new Ticket();
        ticket.setRouteId(ticketDto.getRouteId());
        ticket.setDateTime(ticketDto.getDateTime());
        ticket.setSeatNumber(ticketDto.getSeatNumber());
        ticket.setPrice(ticketDto.getPrice());

        ticketRepository.save(ticket);
        return ResponseEntity.ok().build();
    }

    public void updateTicket(Ticket ticket) {
        ticketRepository.update(ticket);
    }

    public void deleteTicket(Long id) {
        ticketRepository.delete(id);
    }
}
