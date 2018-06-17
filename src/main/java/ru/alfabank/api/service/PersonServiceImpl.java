package ru.alfabank.api.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alfabank.api.domain.Person;
import ru.alfabank.api.exception.NoSuchPersonException;
import ru.alfabank.api.repository.PersonRepository;

import static java.lang.String.format;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Override
    public Person getPerson(String id) {
        return personRepository.getPerson(id)
                .orElseThrow(() -> new NoSuchPersonException(format("Person with id %s is not found", id)));
    }
}
