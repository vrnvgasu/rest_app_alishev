package ru.edu.rest_app_alishev.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.edu.rest_app_alishev.models.Person;
import ru.edu.rest_app_alishev.services.PeopleService;
import ru.edu.rest_app_alishev.util.PersonErrorResponse;
import ru.edu.rest_app_alishev.util.PersonNotFoundException;

@RestController
@RequestMapping("/people")
public class PeopleController {

  private final PeopleService peopleService;

  public PeopleController(PeopleService peopleService) {
    this.peopleService = peopleService;
  }

  @GetMapping()
  public List<Person> getPeople() {
    // Jakcson конвертирует объекты в json в ответе из-за аннотации @RestController
    return peopleService.findAll();
  }

  @GetMapping("/{id}")
  public Person getOne(@PathVariable("id") int id) {
    // Jakcson конвертирует объект в json в ответе из-за аннотации @RestController
    return peopleService.findOne(id);
  }

  @ExceptionHandler // говорит, что перехватывает исключение
  // название метода любое
  // в параметре указали тип перехватываемого исключения PersonNotFoundException
  private ResponseEntity<PersonErrorResponse> handeException(PersonNotFoundException e) {
    PersonErrorResponse response = new PersonErrorResponse(
        "Person with such id not found",
        System.currentTimeMillis()
    );

    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

}
