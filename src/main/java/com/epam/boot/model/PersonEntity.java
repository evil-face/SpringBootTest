package com.epam.boot.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

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

  @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
  @JsonManagedReference
  private Passport passport;

  @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
  @BatchSize(size = 5)
  private List<Book> books = new ArrayList<>();

  //    @ManyToMany(fetch = FetchType.EAGER)
  @ManyToMany
  @JoinTable(
      name = "person_hobby",
      joinColumns = @JoinColumn(name = "person_id"),
      inverseJoinColumns = @JoinColumn(name = "hobby_id"))
  @JsonManagedReference
  private List<Hobby> hobbies = new ArrayList<>();
}
