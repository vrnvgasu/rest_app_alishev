package ru.edu.rest_app_alishev.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController // помечает все методы, как @ResponseBody
@RequestMapping("/api")
public class FirstRestController {

  // если вместо строки возвращаем объект, то Jackson будет
  // пытаться преобразовать его в строку json
  //@ResponseBody // возвращаем не view, а данные
  @GetMapping("/hello")
  public String seyHello() {
    return "Hello, world!";
  }

}
