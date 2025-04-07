package org.repin.repository;

import org.repin.model.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class RouteRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RouteRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Сохранение нового маршрута (INSERT)
    public void save(Route route) {
        String sql = """
            INSERT INTO routes (departure_point, destination_point, carrier_id, duration_minutes)
            VALUES (?, ?, ?, ?)
            """;

        jdbcTemplate.update(
                sql,
                route.getDeparturePoint(),
                route.getDestinationPoint(),
                route.getCarrier_id(),
                route.getDurationMinutes()
        );
    }

    public void update(Route route) {
        String sql = """
            UPDATE routes
            SET departure_point = ?,
                destination_point = ?,
                carrier_id = ?,
                duration_minutes = ?
            WHERE id = ?
            """;

        int updatedRows = jdbcTemplate.update(
                sql,
                route.getDeparturePoint(),
                route.getDestinationPoint(),
                route.getCarrier_id(),
                route.getDurationMinutes(),
                route.getId()
        );

        if (updatedRows == 0) {
            throw new IllegalArgumentException("Маршрут с ID " + route.getId() + " не найден");
        }
    }

    public void delete(Long id) {
        String sql = "DELETE FROM routes WHERE id = ?";
        int deletedRows = jdbcTemplate.update(sql, id);

        if (deletedRows == 0) {
            throw new IllegalArgumentException("Маршрут с ID " + id + " не найден");
        }
    }

    public Optional<Route> find(Long id) {
        String sql = "SELECT * FROM tickets WHERE id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Route.class), id)
                .stream()
                .findFirst();
    }
}