package org.repin.repository;

import org.repin.dto.Ticket;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TicketRepository {

    private final JdbcTemplate jdbcTemplate;

    TicketRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    public void save(Ticket ticket) {
        String sql = "INSERT INTO bought_tickets (route_id, date_time, seat_number, price) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                ticket.getRouteId(),
                ticket.getDateTime(),
                ticket.getSeatNumber(),
                ticket.getPrice());
    }
}
