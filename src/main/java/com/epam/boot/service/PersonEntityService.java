package com.epam.boot.service;

import com.epam.boot.dao.PersonEntityDAO;
import com.epam.boot.exception.PersonNotFoundException;
import com.epam.boot.model.PersonEntity;
import java.util.List;
import org.hibernate.Hibernate;
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

//  @Transactional(readOnly = true)
  public PersonEntity getPersonById(int id) {
    PersonEntity personEntity = personDAO.findById(id).orElseThrow(PersonNotFoundException::new);
//    Hibernate.initialize(personEntity.getBooks());
//    Hibernate.initialize(personEntity.getHobbies());
    return personEntity;
  }

  public void printPeopleWithBooks() {
//    List<PersonEntity> people = personDAO.findAll();
        List<PersonEntity> people = personDAO.findAllWithPassportAndBooks();

    for (PersonEntity person : people) {
      System.out.println(person.getName() + " has " + person.getBooks().size() + " books");
    }
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
