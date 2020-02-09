package uk.ebi.embi.service;

import uk.ebi.embi.domain.Person;

import java.util.List;

public interface PersonService {
    Person savePerson(Person person);

    List<Person> listAllPersons();

    void deletePerson(String person);

    Person updatePerson(Person person);
}
