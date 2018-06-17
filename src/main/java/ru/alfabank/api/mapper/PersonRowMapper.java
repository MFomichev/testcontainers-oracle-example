package ru.alfabank.api.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.alfabank.api.domain.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonRowMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Person(
                rs.getString("ID"),
                rs.getString("LAST_NAME"),
                rs.getString("FIRST_NAME"),
                rs.getString("PHONE_NUMBER"),
                rs.getString("EMAIL")
        );
    }
}