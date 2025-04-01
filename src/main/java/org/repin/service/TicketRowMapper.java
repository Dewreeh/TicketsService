package org.repin.service;

import org.repin.model.Ticket;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketRowMapper implements RowMapper<Ticket> {
    @Override
    public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Ticket(
                rs.getLong("id"),  // Исправлено
                rs.getLong("route_id"),  // Исправлено
                rs.getTimestamp("date_time").toLocalDateTime(),
                rs.getInt("seat_number"),
                rs.getBigDecimal("price")
        );
    }
}