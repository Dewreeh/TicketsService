package org.repin.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.repin.model.Ticket;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaProducerService {

    private final KafkaTemplate<String, Ticket> kafkaTemplate;

    KafkaProducerService(KafkaTemplate<String, Ticket> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendTicketToKafka(Ticket ticket) throws JsonProcessingException {
            kafkaTemplate.send("bought-tickets", ticket);
    }
}
