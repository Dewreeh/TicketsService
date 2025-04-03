package org.repin.service;

import org.repin.dto.Ticket;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "bought-tickets")
    public void consumeTicket(Ticket ticket){

    }
}
