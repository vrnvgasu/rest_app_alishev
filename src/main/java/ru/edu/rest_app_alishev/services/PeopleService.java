package ru.edu.rest_app_alishev.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.edu.rest_app_alishev.models.Person;
import ru.edu.rest_app_alishev.repositories.PeopleRepository;
import ru.edu.rest_app_alishev.util.PersonNotFoundException;

@Service
// Открываем/закрываем транзакции автоматом. Пометили не методы, а целый класс
@Transactional(readOnly = true)
public class PeopleService {

  private final PeopleRepository peopleRepository;

  @Autowired
  public PeopleService(PeopleRepository peopleRepository) {
    this.peopleRepository = peopleRepository;
  }

  // по умолчанию @Transactional(readOnly = true), как для класса
  public List<Person> findAll() {
    return peopleRepository.findAll();
  }

  public Person findOne(int id) {
    Optional<Person> optionalPerson = peopleRepository.findById(id);
    return optionalPerson
        .orElseThrow(PersonNotFoundException::new);
  }

  @Transactional
  public void save(Person person) {
    peopleRepository.save(person);
  }

}
