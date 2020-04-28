package com.boot.start.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.boot.start.domain.Person;

public interface PersonRepository extends JpaRepository<Person, String> {
}
