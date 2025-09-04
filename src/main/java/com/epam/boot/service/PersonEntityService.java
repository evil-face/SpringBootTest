package com.epam.boot.service;

import com.epam.boot.dao.PersonEntityDAO;
import com.epam.boot.exception.PersonNotFoundException;
import com.epam.boot.model.PersonEntity;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonEntityService {
  private final PersonEntityDAO personDAO;

  public PersonEntityService(PersonEntityDAO personDAO) {
    this.personDAO = personDAO;
  }

  public List<PersonEntity> getAllPeople() {
    return personDAO.findAll();
  }

  public PersonEntity getPersonById(int id) {
    return personDAO.findById(id).orElseThrow(PersonNotFoundException::new);
  }

  public PersonEntity getPersonByEmail(String email) {
    return personDAO.findByEmail(email).orElseThrow(PersonNotFoundException::new);
  }

  @Transactional
  public void createPerson(PersonEntity person) {
    personDAO.save(person);
  }

  @Transactional
  public void updatePerson(PersonEntity person) {
    personDAO.update(person);
  }

  @Transactional
  public void deletePerson(int id) {
    personDAO.delete(id);
  }
}
