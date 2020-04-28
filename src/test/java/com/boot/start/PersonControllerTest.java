package com.boot.start;

import com.boot.start.domain.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.boot.start.service.PersonService;

import java.util.Arrays;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class PersonControllerTest {

    @Autowired
    protected MockMvc mockMvc;
    @MockBean
    protected PersonService personService;
    @Autowired
    protected ObjectMapper objectMapper;
    private Person person;
    private static final String PATH = "/api/persons";

    @Before
    public void init() {
        person = new Person(UUID.randomUUID().toString(), "John", "Doe", 50, "red", Arrays.asList("cooking", "fishing"));
    }

    @Test
    public void shouldCreatePerson() throws Exception {
        // given
        when(personService.savePerson(any(Person.class))).thenReturn(person);
        // when
        ResultActions actualResult = this.mockMvc.perform(MockMvcRequestBuilders
                .put(PATH)
                .content(json(person))
                .contentType(MediaType.APPLICATION_JSON_UTF8));
        // then
        actualResult
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.first_name").value("John"));
    }

    @Test
    public void shouldUpdatePerson() throws Exception {
        // given
        person.setAge(55);
        person.setFirstName("Jack");
        when(personService.updatePerson(any(Person.class))).thenReturn(person);
        // when
        ResultActions actualResult = this.mockMvc.perform(MockMvcRequestBuilders
                .post(PATH)
                .content(json(person))
                .contentType(MediaType.APPLICATION_JSON_UTF8));
        // then
        actualResult
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.age").value(55))
                .andExpect(jsonPath("$.first_name").value("Jack"));
    }

    @Test
    public void shouldListPersons() throws Exception {
        // given
        when(personService.listAllPersons()).thenReturn(Arrays.asList(person, person, person));
        // when
        ResultActions actualResult = this.mockMvc.perform(MockMvcRequestBuilders.get(PATH));
        // then
        actualResult
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(hasSize(3)));
    }

    @Test
    public void shouldDeletePerson() throws Exception {
        String id = "123";
        // when
        ResultActions actualResult = this.mockMvc.perform(MockMvcRequestBuilders.delete(PATH + "/" + id));
        // then
        actualResult
                .andExpect(status().isOk());
        verify(personService, times(1)).deletePerson(anyString());
    }


    private String json(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
