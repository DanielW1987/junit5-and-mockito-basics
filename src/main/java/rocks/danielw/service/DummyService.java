package rocks.danielw.service;

import rocks.danielw.model.Owner;
import rocks.danielw.repository.CrudRepository;

import java.util.Optional;

public class DummyService {

  private final CrudRepository<Long, Owner> repository;

  public DummyService(CrudRepository<Long, Owner> repository) {
    this.repository = repository;
  }

  public Optional<Owner> findById(Long id) {
    return Optional.ofNullable(repository.find(id));
  }

  public Long create(Owner owner) {
    return repository.create(owner);
  }

  public boolean delete(long id) {
    try {
      repository.delete(id);
      return true;
    }
    catch (RuntimeException e) {
      return false;
    }
  }

}
