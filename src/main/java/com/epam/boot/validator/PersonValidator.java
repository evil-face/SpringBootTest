package com.epam.boot.validator;

import com.epam.boot.model.Person;
import com.epam.boot.service.PersonService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

  private final PersonService personService;

  public PersonValidator(PersonService personService) {
    this.personService = personService;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return Person.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    Person person = (Person) target;
    Person existingPerson = personService.getPersonByEmail(person.getEmail());

    if (existingPerson.getId() != person.getId()) {
      errors.rejectValue("email", "", "Email already taken");
    }
  }
}
