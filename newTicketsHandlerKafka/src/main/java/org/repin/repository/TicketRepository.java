package org.repin.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TicketRepository {

    private final JdbcTemplate jdbcTemplate;

    TicketRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    public void markAsSold(Long ticketId, Long userId) {
        String sql = "INSERT INTO user_tickets (user_id, ticket_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, userId, ticketId);
    }
}
