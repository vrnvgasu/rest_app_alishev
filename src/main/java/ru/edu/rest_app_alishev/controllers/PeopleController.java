package ru.edu.rest_app_alishev.controllers;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.edu.rest_app_alishev.dto.PersonDTO;
import ru.edu.rest_app_alishev.models.Person;
import ru.edu.rest_app_alishev.services.PeopleService;
import ru.edu.rest_app_alishev.util.PersonErrorResponse;
import ru.edu.rest_app_alishev.util.PersonNotCreatedException;
import ru.edu.rest_app_alishev.util.PersonNotFoundException;

@RestController
@RequestMapping("/people")
public class PeopleController {

  private final PeopleService peopleService;

  private final ModelMapper modelMapper;

  public PeopleController(PeopleService peopleService, ModelMapper modelMapper) {
    this.peopleService = peopleService;
    this.modelMapper = modelMapper;
  }

  @GetMapping()
  public List<PersonDTO> getPeople() {
    // Jakcson конвертирует объекты в json в ответе из-за аннотации @RestController
    return peopleService.findAll().stream()
        .map(this::convertToPersonDTO).collect(Collectors.toList());
  }

  @GetMapping("/{id}")
  public PersonDTO getOne(@PathVariable("id") int id) {
    // Jakcson конвертирует объект в json в ответе из-за аннотации @RestController
    return convertToPersonDTO(peopleService.findOne(id));
  }

  @PostMapping
  public ResponseEntity<HttpStatus> create(@RequestBody @Valid PersonDTO personDTO,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      StringBuilder errorMessage = new StringBuilder();
      List<FieldError> errors = bindingResult.getFieldErrors();

      for (FieldError error : errors) {
        errorMessage.append(error.getField())
            .append(" - ")
            .append(error.getDefaultMessage())
            .append(";");
      }

      throw new PersonNotCreatedException(errorMessage.toString());
    }

    peopleService.save(convertToPerson(personDTO));

    // ответ с пустым телом и статусом 200
    return new ResponseEntity<>(HttpStatus.OK);
  }

  private Person convertToPerson(PersonDTO personDTO) {
    // находит все одинаковые поля в классах и заполняет их
    return modelMapper.map(personDTO, Person.class);
  }

  private PersonDTO convertToPersonDTO(Person person) {
    return modelMapper.map(person, PersonDTO.class);
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

  @ExceptionHandler // говорит, что перехватывает исключение
  private ResponseEntity<PersonErrorResponse> handeException(PersonNotCreatedException e) {
    PersonErrorResponse response = new PersonErrorResponse(
        e.getMessage(),
        System.currentTimeMillis()
    );

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

}
