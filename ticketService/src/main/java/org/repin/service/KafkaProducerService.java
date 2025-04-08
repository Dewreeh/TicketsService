package org.repin.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.repin.model.Ticket;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaProducerService{

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate,
                         ObjectMapper objectMapper){
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendTicketToKafka(Ticket ticket){
        try {
            String str = objectMapper.writeValueAsString(ticket);
            kafkaTemplate.send("bought-tickets", str);
        } catch (JsonProcessingException ex){
            log.error("Ошибка сериализации билета в JSON: {}", ticket.getId(), ex);
            throw new RuntimeException(ex);
        }
    }
}
