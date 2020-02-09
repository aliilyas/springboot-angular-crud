package uk.ebi.embi.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uk.ebi.embi.domain.Person;
import uk.ebi.embi.service.PersonService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public List<Person> listAll() {
        log.debug("listAll()");
        return personService.listAllPersons();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(path = "/{uuid}")
    public void delete(@PathVariable String uuid) {
        log.debug("deleteTask(uuid={})", uuid);
        personService.deletePerson(uuid);
    }

    @PutMapping
    public @ResponseBody Person savePerson(@RequestBody @Valid Person person) {
        log.debug("savePerson(personDTO={})", person);
        return personService.savePerson(person);
    }

    @PostMapping
    public @ResponseBody Person updatePerson(@RequestBody Person person) {
        log.debug("updatePerson(personDTO={})", person);
        return personService.updatePerson(person);
    }

}
