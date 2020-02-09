package uk.ebi.embi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.ebi.embi.domain.Person;

public interface PersonRepository extends JpaRepository<Person, String> {
}
