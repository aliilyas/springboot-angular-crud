package com.boot.start.service;

import com.boot.start.domain.Person;

import java.util.List;

public interface PersonService {
    Person savePerson(Person person);

    List<Person> listAllPersons();

    void deletePerson(String person);

    Person updatePerson(Person person);
}
