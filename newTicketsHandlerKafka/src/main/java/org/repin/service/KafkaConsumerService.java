package org.repin.service;

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
    KafkaConsumerService(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }
    @KafkaListener(topics = "bought-tickets")
    public void consumeTicket(Ticket ticket){
       log.info("Принят билет {}", ticket);
    }
}
