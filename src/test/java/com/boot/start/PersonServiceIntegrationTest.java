package com.boot.start;

import com.boot.start.domain.Person;
import com.boot.start.exception.EntityNotFoundException;
import com.boot.start.repository.PersonRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import com.boot.start.service.PersonService;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class PersonServiceIntegrationTest {

    @Autowired
    private PersonService personService;
    @Autowired
    private PersonRepository repository;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Person person = null;

    @Before
    public void preDefine() {
        person = Person.builder().firstName("test").lastName("test").favouriteColour("red").age(50).build();
        person = repository.save(person);
    }

    @After
    public void clean() {
        repository.deleteAll();
    }

    @Test
    public void testPersonSave() {
        //given
        Person person = Person.builder().firstName("test").lastName("test").favouriteColour("red").hobby(Arrays.asList("fishing", "cooking")).age(50).build();
        //when
        Person saved = personService.savePerson(person);
        //then
        assertThat(saved.getId(), is(notNullValue()));
        assertThat(personService.listAllPersons(), hasSize(2));
    }

    @Test
    public void testUpdatePerson() {
        //when
        Person target = personService.updatePerson(Person.builder().id(person.getId()).firstName("A").lastName("B").build());
        //then
        assertThat(target.getId(), is(notNullValue()));
        assertThat(target.getFirstName(), is("A"));
        assertThat(target.getLastName(), is("B"));
    }

    @Test
    public void testDeletion() {
        //when
        personService.deletePerson(person.getId());
        //then
        assertThat(personService.listAllPersons(), is(empty()));
    }

    @Test
    public void testReadAll() {
        //when
        //then
        assertThat(personService.listAllPersons(), hasSize(1));
    }

    @Test
    public void deleteThowsException() {
        //given
        thrown.expect(EntityNotFoundException.class);
        thrown.expectMessage(containsString("Entity of type [Person] with id [ABC] not found"));
        //when
        personService.deletePerson("ABC");
    }

    @Test
    public void updateThowsException() {
        //given
        thrown.expect(EntityNotFoundException.class);
        thrown.expectMessage(containsString("Entity of type [Person] with id [ABC] not found"));
        //when
        personService.updatePerson(Person.builder().id("ABC").build());
    }
}
