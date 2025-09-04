package com.epam.boot.validator;

import com.epam.boot.dao.PersonDAO;
import com.epam.boot.model.Person;
import java.util.Optional;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

  private final PersonDAO personDAO;

  public PersonValidator(PersonDAO personDAO) {
    this.personDAO = personDAO;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return Person.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    Person person = (Person) target;
    Optional<Person> existingPerson = personDAO.findByEmail(person.getEmail());

    if (existingPerson.isPresent() && existingPerson.get().getId() != person.getId()) {
      errors.rejectValue("email", "", "Email already taken");
    }
  }
}
