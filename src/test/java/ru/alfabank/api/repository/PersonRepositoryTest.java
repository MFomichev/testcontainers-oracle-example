package ru.alfabank.api.repository;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.alfabank.api.domain.Person;
import ru.alfabank.api.mapper.PersonRowMapper;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersonRepositoryTest {

    private PersonRepository repository;
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        jdbcTemplate = mock(JdbcTemplate.class);
        repository = new PersonRepositoryImpl(jdbcTemplate);
    }

    @Test
    public void testReturnPerson() {
        Person expectedPerson = new Person("007","Иванов", "Иван", "9095551122", "ivan@ivanov.net");
        when(jdbcTemplate.queryForObject(any(), isA(PersonRowMapper.class), eq("007"))).thenReturn(expectedPerson);
        Optional<Person> optional = repository.getPerson("007");
        assertTrue(optional.isPresent());
        assertEquals(expectedPerson, optional.get());
    }

    @Test
    public void testNoSuchPerson() {
        when(jdbcTemplate.queryForObject(any(), isA(PersonRowMapper.class), eq("007")))
                .thenThrow(new EmptyResultDataAccessException(1));
        Optional<Person> optional = repository.getPerson("007");
        assertFalse(optional.isPresent());
    }

}