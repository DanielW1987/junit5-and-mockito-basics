package rocks.danielw.repository;

import java.util.Optional;

public interface CrudRepository<ID, T> {

  T find(ID id);

  Iterable<T> findAll();

  ID create(T obj);

  T update(T obj);

  boolean delete(ID id);

}
