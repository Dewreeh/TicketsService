package org.repin.repository;

import org.repin.exceptions.UserAlreadyExistsException;
import org.repin.model.User;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(User user) throws Exception {
        String saveUserQuery = "INSERT INTO users (login, password, full_name) VALUES (?, ?, ?)";

        try {
            jdbcTemplate.update(saveUserQuery, user.getLogin(), user.getPassword(), user.getFullName());
        } catch (DuplicateKeyException e) {
            throw new UserAlreadyExistsException("Пользователь уже существует! " + user.getLogin());
        }
    }

    public Optional<User> find(Long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), id)
                .stream()
                .findFirst();
    }

    public Optional<User> findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE login = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), username)
                .stream()
                .findFirst();
    }

}
