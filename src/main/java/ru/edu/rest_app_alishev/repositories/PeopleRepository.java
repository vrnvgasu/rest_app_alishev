package ru.edu.rest_app_alishev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.edu.rest_app_alishev.models.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

}
