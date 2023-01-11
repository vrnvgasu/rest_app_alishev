package ru.edu.rest_app_alishev.util;

public class PersonNotCreatedException extends RuntimeException {

  public PersonNotCreatedException(String message) {
    super(message);
  }

}
