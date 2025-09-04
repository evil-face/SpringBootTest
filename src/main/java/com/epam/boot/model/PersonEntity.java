package com.epam.boot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "person")
@Getter
@Setter
public class PersonEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NotEmpty(message = "Name should not be empty")
  @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
  @Column(nullable = false, length = 30)
  private String name;

  @Min(value = 0, message = "Age should be bigger than 0")
  @Column(nullable = false)
  private int age;

  @NotEmpty(message = "Email should not be empty")
  @Email(message = "Email should be valid")
  @Column(nullable = false, unique = true)
  private String email;
}
