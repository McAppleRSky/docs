package ru.mrs.docs.service.db.dao;

import java.util.List;
import java.util.Optional;

public interface DaoOperations<Entity, Id> {

    List<Entity> findAll();

    Optional<Entity> findById(Id id);

    boolean saveNew(Entity entity);

    boolean merge(Entity entity);

    boolean deleteById(Id id);

}
