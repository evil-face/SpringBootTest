package com.epam.boot.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "hobby")
@Getter
@Setter
public class Hobby {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(nullable = false, unique = true)
  private String name;

  @ManyToMany(mappedBy = "hobbies")
  @JsonBackReference
  private List<PersonEntity> people = new ArrayList<>();
}
