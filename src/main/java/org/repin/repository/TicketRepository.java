package org.repin.repository;

import org.repin.model.Ticket;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TicketRepository {

    private final JdbcTemplate jdbcTemplate;

    public TicketRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Ticket> findAvailableTickets(String departure, String destination, String carrier, LocalDateTime dateTime, int limit, int offset) {
        String sql = "SELECT t.* FROM tickets t " +
                "JOIN routes r ON t.route_id = r.id " +
                "JOIN carriers c ON r.carrier_id = c.id " +
                "WHERE NOT EXISTS (SELECT 1 FROM user_tickets ut WHERE ut.ticket_id = t.id) " +
                (departure != null ? "AND r.departure_point ILIKE ? " : "") +
                (destination != null ? "AND r.destination_point ILIKE ? " : "") +
                (carrier != null ? "AND c.name ILIKE ? " : "") +
                (dateTime != null ? "AND t.date_time >= ? " : "") +
                " ORDER BY t.date_time LIMIT ? OFFSET ?";

        List<Object> params = new ArrayList<>();
        if (departure != null) params.add("%" + departure + "%");
        if (destination != null) params.add("%" + destination + "%");
        if (carrier != null) params.add("%" + carrier + "%");
        if (dateTime != null) params.add(dateTime);
        params.add(limit);
        params.add(offset);

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Ticket.class), params.toArray());
    }

    public Optional<Ticket> findById(Long ticketId) {
        String sql = "SELECT * FROM tickets WHERE id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Ticket.class), ticketId)
                .stream()
                .findFirst();
    }

    public void markAsSold(Long ticketId, Long userId) {
        String sql = "INSERT INTO user_tickets (user_id, ticket_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, userId, ticketId);
    }
}
