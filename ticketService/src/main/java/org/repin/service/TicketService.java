package org.repin.service;

import org.repin.dto.ErrorResponse;
import org.repin.model.Ticket;
import org.repin.repository.TicketRepository;
import org.repin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final RedisTemplate<Long, Ticket> redisTemplate;

    @Autowired
    TicketService(TicketRepository ticketRepository,
                         UserRepository userRepository,
                         RedisTemplate<Long, Ticket> redisTemplate) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.redisTemplate = redisTemplate;
    }

    public List<Ticket> findAvailableTickets(String departure, String destination, String carrier, LocalDateTime dateTime, int limit, int offset){
        return ticketRepository.findAvailableTickets(departure, destination, carrier, dateTime, limit, offset);
    }

    public ResponseEntity<Object> findUserTickets(Long userId){
        if(userRepository.find(userId).isEmpty()){
            return ResponseEntity.badRequest().body(new ErrorResponse("Пользователь не найден"));
        }

        List<Ticket> tickets = redisTemplate.opsForList().range(userId, 0, -1);

        if(!tickets.isEmpty()){
            return ResponseEntity.ok(tickets);
        }

        tickets = ticketRepository.findUserTickets(userId);

        redisTemplate.opsForList().rightPushAll(userId, tickets);

        return ResponseEntity.ok(tickets);
    }
    @Transactional
    public ResponseEntity<Object> buyTicket(Long ticketId, Long userId) {
        if(userRepository.find(userId).isEmpty()){
            return ResponseEntity.badRequest().body(new ErrorResponse("Пользователь не найден"));
        }

        if(ticketRepository.find(ticketId).isEmpty()){
            return ResponseEntity.badRequest().body(new ErrorResponse("Билет не найден"));
        }

        if(!ticketRepository.checkAvailability(ticketId)){
            return ResponseEntity.badRequest().body(new ErrorResponse("Билет уже куплен"));
        }

        ticketRepository.markAsSold(ticketId, userId);

        redisTemplate.opsForList().rightPush(userId, ticketRepository.find(ticketId).get()); //мы ранее уже проверили наличие билета по id, значит тут точно не будет null

        return ResponseEntity.ok("Билет приобретён");
    }
}
