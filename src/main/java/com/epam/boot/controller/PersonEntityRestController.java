package com.epam.boot.controller;

import com.epam.boot.model.PersonEntity;
import com.epam.boot.service.PersonEntityService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hibernate/people")
public class PersonEntityRestController {
  private final PersonEntityService personEntityService;

  public PersonEntityRestController(PersonEntityService personEntityService) {
    this.personEntityService = personEntityService;
  }

  @GetMapping
  public List<PersonEntity> getAllPeople() {
    return personEntityService.getAllPeople();
  }

  @GetMapping("/{id}")
  public PersonEntity getPersonById(@PathVariable int id) {
    return personEntityService.getPersonById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PersonEntity createPerson(@RequestBody @Valid PersonEntity person) {
    personEntityService.createPerson(person);
    return person;
  }

  @PutMapping("/{id}")
  public PersonEntity updatePerson(
      @PathVariable int id, @RequestBody @Valid PersonEntity updatedPerson) {
    PersonEntity existing = personEntityService.getPersonById(id);
    updatedPerson.setId(id);
    personEntityService.updatePerson(updatedPerson);
    return updatedPerson;
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletePerson(@PathVariable int id) {
    PersonEntity existing = personEntityService.getPersonById(id);
    personEntityService.deletePerson(id);
  }
}
