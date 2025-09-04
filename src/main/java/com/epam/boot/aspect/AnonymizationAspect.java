package com.epam.boot.aspect;

import com.epam.boot.model.Person;
import java.util.Set;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AnonymizationAspect {
  private static final Set<String> SECRET_LIST = Set.of("Laura Hall", "Oliver King");

  @AfterReturning(
      pointcut = "execution(com.epam.boot.model.Person com.epam.boot.service.PersonService.*(..))",
      returning = "person")
  public void anonymizePerson(Person person) {
    if (person != null && SECRET_LIST.contains(person.getName())) {
      person.setEmail("*****@*****.com");
    }
  }
}
