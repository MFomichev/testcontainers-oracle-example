package ru.alfabank.api.repository;

import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.alfabank.api.domain.Person;
import ru.alfabank.api.mapper.PersonRowMapper;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class PersonRepositoryImpl implements PersonRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Person> getPerson(String id) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(
                    "SELECT * FROM PERSON WHERE ID=?",
                    new PersonRowMapper(),
                    id
            ));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

    }
}
