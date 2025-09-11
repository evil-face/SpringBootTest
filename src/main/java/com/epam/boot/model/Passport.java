package com.epam.boot.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

@Entity
@Table(name = "passport")
@Getter
@Setter
public class Passport {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(nullable = false, unique = true, length = 20)
  private String number;

  @OneToOne
  @JoinColumn(name = "person_id", referencedColumnName = "id")
  @JsonBackReference
  private PersonEntity person;
}
