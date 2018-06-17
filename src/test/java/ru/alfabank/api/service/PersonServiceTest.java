package ru.alfabank.api.service;

import org.junit.Before;
import org.junit.Test;
import ru.alfabank.api.domain.Person;
import ru.alfabank.api.exception.NoSuchPersonException;
import ru.alfabank.api.repository.PersonRepository;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersonServiceTest {

    private PersonService service;
    private PersonRepository repository;

    @Before
    public void setUp() {
        repository = mock(PersonRepository.class);
        service = new PersonServiceImpl(repository);
    }

    @Test
    public void testServiceReturnPerson() {
        Person expectedPerson = new Person("007","Иванов", "Иван", "9095551122", "ivan@ivanov.net");
        when(repository.getPerson("007")).thenReturn(Optional.of(expectedPerson));
        assertEquals(expectedPerson, service.getPerson("007"));
    }

    @Test(expected = NoSuchPersonException.class)
    public void testIfRepositoryNotReturnPerson() {
        when(repository.getPerson("007")).thenReturn(Optional.empty());
        service.getPerson("007");
    }

}

