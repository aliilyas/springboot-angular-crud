package com.boot.start.service;

import com.boot.start.domain.Person;
import com.boot.start.exception.EntityNotFoundException;
import com.boot.start.repository.PersonRepository;
import com.boot.start.util.Copy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DefaultPersonService implements PersonService {

    private final PersonRepository repository;

    @Override
    @Transactional
    public Person savePerson(Person person) {
        return repository.save(person);
    }

    @Override
    public List<Person> listAllPersons() {
        return repository.findAll();
    }

    @Override
    public void deletePerson(String id) {
        Person savedPerson = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(Person.class, id));
        repository.delete(savedPerson);
    }

    @Override
    @Transactional
    public Person updatePerson(Person person) {
        Person savedPerson = repository.findById(person.getId()).orElseThrow(() -> new EntityNotFoundException(Person.class, person.getId()));
        Copy.copyProperties(person, savedPerson);
        return repository.save(savedPerson);
    }
}
