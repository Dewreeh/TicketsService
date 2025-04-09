package org.repin.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.repin.dto.Ticket;
import org.repin.repository.TicketRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumerService {
    private final TicketRepository ticketRepository;
    private final ObjectMapper objectMapper;
    KafkaConsumerService(TicketRepository ticketRepository,
                         ObjectMapper objectMapper){
        this.ticketRepository = ticketRepository;
        this.objectMapper = objectMapper;
    }
    @KafkaListener(topics = "bought-tickets", groupId = "test")
    public void consumeTicket(String message) throws JsonProcessingException {
       Ticket ticket = objectMapper.readValue(message, Ticket.class);
       ticketRepository.save(ticket);
       log.info("Принят билет {}", message);
    }
}
