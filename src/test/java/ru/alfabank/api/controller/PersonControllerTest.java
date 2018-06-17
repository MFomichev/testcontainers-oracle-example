package ru.alfabank.api.controller;

import org.junit.Before;
import org.junit.Test;
import ru.alfabank.api.domain.Person;
import ru.alfabank.api.exception.NoSuchPersonException;
import ru.alfabank.api.service.PersonService;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersonControllerTest {

    private PersonController controller;
    private PersonService personService;

    @Before
    public void setUp() {
        personService = mock(PersonService.class);
        controller = new PersonController(personService);
    }

    @Test
    public void testReturnPerson() {
        Person expectedPerson = new Person("007","Иванов", "Иван", "9095551122", "ivan@ivanov.net");
        when(personService.getPerson("007")).thenReturn(expectedPerson);
        assertEquals(expectedPerson, controller.getPerson("007"));
    }

    @Test
    public void testExceptionHandling() {
        String expected = "Very good message";
        assertEquals(expected, controller.notFound(new NoSuchPersonException(expected)).getMessage());
    }

}