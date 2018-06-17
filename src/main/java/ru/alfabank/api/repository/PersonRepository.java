package ru.alfabank.api.repository;

import ru.alfabank.api.domain.Person;

import java.util.Optional;

public interface PersonRepository {
    Optional<Person> getPerson(String id);
}
