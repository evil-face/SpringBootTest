package com.epam.boot.dao;

import com.epam.boot.model.Person;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class PersonDAO {
  private final JdbcTemplate jdbcTemplate;

  @Autowired
  PersonDAO(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Person> findAll() {
    return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
  }

  public Optional<Person> findById(int id) {
    return jdbcTemplate
        .query("SELECT * FROM Person WHERE id=?", new BeanPropertyRowMapper<>(Person.class), id)
        .stream()
        .findFirst();
  }

  public Optional<Person> findByEmail(String email) {
    return jdbcTemplate
        .query(
            "SELECT * FROM person WHERE email=?", new BeanPropertyRowMapper<>(Person.class), email)
        .stream()
        .findFirst();
  }

  public void save(Person person) {
    jdbcTemplate.update(
        "INSERT INTO Person(name, age, email) VALUES(?, ?, ?)",
        person.getName(),
        person.getAge(),
        person.getEmail());
  }

  public void update(int id, Person updatedPerson) {
    jdbcTemplate.update(
        "UPDATE Person SET name=?, age=?, email=? WHERE id=?",
        updatedPerson.getName(),
        updatedPerson.getAge(),
        updatedPerson.getEmail(),
        id);
  }

  public void delete(int id) {
    jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
  }
}
