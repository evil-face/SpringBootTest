package com.epam.boot.controller;

import com.epam.boot.model.Person;
import com.epam.boot.service.PersonService;
import com.epam.boot.validator.PersonValidator;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PersonController {
  private final PersonService personService;
  private final PersonValidator personValidator;

  public PersonController(PersonService personService, PersonValidator personValidator) {
    this.personService = personService;
    this.personValidator = personValidator;
  }

  @GetMapping
  public String index(Model model) {
    model.addAttribute("people", personService.getAllPeople());
    return "people/index";
  }

  @GetMapping("/{id}")
  public String show(@PathVariable("id") int id, Model model) {
    model.addAttribute("person", personService.getPersonById(id));
    return "people/show";
  }

  @GetMapping("/new")
  public String newPerson(@ModelAttribute("person") Person person) {
    return "people/new";
  }

  @PostMapping
  public String create(
          @ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
    personValidator.validate(person, bindingResult);

    if (bindingResult.hasErrors()) {
      return "people/new";
    }

    personService.createPerson(person);
    return "redirect:/people";
  }

  @GetMapping("/{id}/edit")
  public String edit(@PathVariable("id") int id, Model model) {
    model.addAttribute("person", personService.getPersonById(id));
    return "people/edit";
  }

  @PatchMapping("/{id}")
  public String update(
      @ModelAttribute("person") @Valid Person person,
      BindingResult bindingResult,
      @PathVariable("id") int id) {
    personValidator.validate(person, bindingResult);

    if (bindingResult.hasErrors()) {
      return "people/edit";
    }
    personService.updatePerson(id, person);
    return "redirect:/people";
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable("id") int id) {
    personService.deletePerson(id);
    return "redirect:/people";
  }
}
