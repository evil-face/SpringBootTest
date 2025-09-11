package com.epam.boot.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "book")
@Getter
@Setter
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(nullable = false)
  private String title;

  @ManyToOne
  @JoinColumn(name = "person_id", referencedColumnName = "id")
  @JsonBackReference
  // or @JsonIgnore
  private PersonEntity owner;
}
