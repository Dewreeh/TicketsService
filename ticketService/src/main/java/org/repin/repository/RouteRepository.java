package org.repin.repository;

import org.repin.model.Route;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RouteRepository {

    private final JdbcTemplate jdbcTemplate;

    public RouteRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Route> findAll() {
        String sql = "SELECT * FROM routes";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Route.class));
    }
}
