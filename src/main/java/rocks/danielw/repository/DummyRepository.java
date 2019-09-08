package rocks.danielw.repository;

import rocks.danielw.model.Owner;

import java.util.List;

public class DummyRepository<ID, T> implements CrudRepository<Long, Owner> {

  @Override
  public Owner find(Long aLong) {
    // do something with the database
    return null;
  }

  @Override
  public Iterable<Owner> findAll() {
    // do something with the database
    return List.of();
  }

  @Override
  public Long create(Owner obj) {
    // do something with the database
    return 0L;
  }

  @Override
  public Owner update(Owner obj) {
    // do something with the database
    return null;
  }

  @Override
  public boolean delete(Long aLong) {
    // do something with the database
    return false;
  }

}
