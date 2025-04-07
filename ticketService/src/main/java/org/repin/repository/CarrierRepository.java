package org.repin.repository;

import org.repin.model.Carrier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class CarrierRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CarrierRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public void save(Carrier carrier){
        String sql = "INSERT INTO carriers (name, phone) VALUES(?, ?)";

        jdbcTemplate.update(sql, carrier.getName(), carrier.getPhone());

    }

    public void update(Carrier carrier) {
        String sql = """
            UPDATE carriers
            SET name = ?,
                phone = ?
            WHERE id = ?
            """;

        int updatedRows = jdbcTemplate.update(
                sql,
                carrier.getName(),
                carrier.getPhone(),
                carrier.getId()
        );

        if (updatedRows == 0) {
            throw new IllegalArgumentException("Перевозчик с ID " + carrier.getId() + " не найден");
        }
    }


    public void delete(Long id) {
        String sql = "DELETE FROM carriers WHERE id = ?";
        int deletedRows = jdbcTemplate.update(sql, id);

        if (deletedRows == 0) {
            throw new IllegalArgumentException("Перевозчик с ID " + id + " не найден");
        }
    }



}