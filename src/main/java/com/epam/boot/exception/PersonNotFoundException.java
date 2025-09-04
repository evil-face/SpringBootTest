package com.epam.boot.exception;

public class PersonNotFoundException extends RuntimeException {
  public PersonNotFoundException() {
    super("Unfortunately, there is not such person in our database :(");
  }
}
