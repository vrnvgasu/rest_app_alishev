package ru.edu.rest_app_alishev.models;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "person")
public class Person {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name")
  @NotEmpty(message = "Name should not be empty")
  @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
  private String name;

  @Column(name = "age")
  @Min(value = 0, message = "Age should be greater than 0")
  private int age;

  @Column(name = "email")
  @Email
  @NotEmpty(message = "Email should not be empty")
  private String email;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updateddAt;

  @Column(name = "created_who")
  @NotEmpty
  private String createdWho;

  public Person() {
  }

  public Person(int id, String name, int age, String email, LocalDateTime createdAt, LocalDateTime updateddAt,
      String createdWho) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.email = email;
    this.createdAt = createdAt;
    this.updateddAt = updateddAt;
    this.createdWho = createdWho;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getUpdateddAt() {
    return updateddAt;
  }

  public void setUpdateddAt(LocalDateTime updateddAt) {
    this.updateddAt = updateddAt;
  }

  public String getCreatedWho() {
    return createdWho;
  }

  public void setCreatedWho(String createdWho) {
    this.createdWho = createdWho;
  }

}
