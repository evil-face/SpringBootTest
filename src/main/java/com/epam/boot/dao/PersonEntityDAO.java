package com.epam.boot.dao;

import com.epam.boot.model.PersonEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class PersonEntityDAO {
  @PersistenceContext private EntityManager entityManager;

  public List<PersonEntity> findAll() {
    return entityManager
        .createQuery("SELECT p FROM PersonEntity p", PersonEntity.class)
        .getResultList();
  }

  public Optional<PersonEntity> findById(int id) {
    return Optional.ofNullable(entityManager.find(PersonEntity.class, id));
  }

  public void save(PersonEntity person) {
    entityManager.persist(person);
  }

  public void update(PersonEntity person) {
    entityManager.merge(person);
  }

  public void delete(int id) {
    PersonEntity person = entityManager.find(PersonEntity.class, id);
    if (person != null) {
      entityManager.remove(person);
    }
  }

  public Optional<PersonEntity> findByEmail(String email) {
    return entityManager
        .createQuery("SELECT p FROM PersonEntity p WHERE p.email = :email", PersonEntity.class)
        .setParameter("email", email)
        .getResultStream()
        .findFirst();
  }
}
