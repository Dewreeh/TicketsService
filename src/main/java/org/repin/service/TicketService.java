package org.repin.service;

import org.repin.model.Ticket;
import org.repin.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> findAvailableTickets(String departure, String destination, String carrier, LocalDateTime dateTime, int limit, int offset){
        return ticketRepository.findAvailableTickets(departure, destination, carrier, dateTime, limit, offset);
    }

    public List<Ticket> findUserTickets(Long userId){
        return ticketRepository.findUserTickets(userId);
    }
    @Transactional
    public Boolean buyTicket(Long ticketId, Long userid){
        if(ticketRepository.checkAvailability(ticketId)){
            ticketRepository.markAsSold(ticketId, userid);
            return true;
        }
        return false;
    }
}
