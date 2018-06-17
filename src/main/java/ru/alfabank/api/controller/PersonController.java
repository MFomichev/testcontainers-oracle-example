package ru.alfabank.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.alfabank.api.domain.ExceptionSerialize;
import ru.alfabank.api.domain.Person;
import ru.alfabank.api.exception.NoSuchPersonException;
import ru.alfabank.api.service.PersonService;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@AllArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping("/person/{id}")
    public Person getPerson(@PathVariable String id) {
        return personService.getPerson(id);
    }

    @ExceptionHandler(NoSuchPersonException.class)
    @ResponseStatus(NOT_FOUND)
    public ExceptionSerialize notFound(NoSuchPersonException exception) {
        return new ExceptionSerialize(exception.getMessage());
    }
}
