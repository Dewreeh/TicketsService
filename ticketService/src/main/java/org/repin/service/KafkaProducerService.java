package org.repin.service;

import lombok.extern.slf4j.Slf4j;
import org.repin.model.Ticket;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaProducerService{

    private final KafkaTemplate<String, Ticket> kafkaTemplate;

    KafkaProducerService(KafkaTemplate<String, Ticket> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendTicketToKafka(Ticket ticket){
            kafkaTemplate.send("bought-tickets", ticket);
    }
}
