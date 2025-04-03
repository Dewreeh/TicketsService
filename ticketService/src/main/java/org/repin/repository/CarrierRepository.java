package org.repin.repository;

import org.repin.model.Carrier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarrierRepository {

    private final JdbcTemplate jdbcTemplate;

    public CarrierRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Carrier> findAll() {
        String sql = "SELECT * FROM carriers";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Carrier.class));
    }
}
