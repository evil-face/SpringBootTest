package com.epam.boot.service;

import com.epam.boot.dao.PersonDAO;
import com.epam.boot.exception.PersonNotFoundException;
import com.epam.boot.model.Person;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonService {

  private final PersonDAO personDAO;

  public PersonService(PersonDAO personDAO) {
    this.personDAO = personDAO;
  }

  public List<Person> getAllPeople() {
    return personDAO.findAll();
  }

  public Person getPersonById(int id) {
    return personDAO.findById(id).orElseThrow(PersonNotFoundException::new);
  }

  public Person getPersonByEmail(String email) {
    return personDAO.findByEmail(email).orElseThrow(PersonNotFoundException::new);
  }

  @Transactional
  public void createPerson(Person person) {
    personDAO.save(person);
  }

  @Transactional
  public void updatePerson(int id, Person person) {
    personDAO.update(id, person);
  }

  @Transactional
  public void deletePerson(int id) {
    personDAO.delete(id);
  }
}
