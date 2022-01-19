package ru.mrs.docs.service.db;

import ru.mrs.docs.service.db.entity.MainEntity;

import java.util.List;

public interface MainService {

    List<MainEntity> getAll();

    MainEntity getById(long id);

    void add(MainEntity entity);

    void update(MainEntity entity);

    void remove(long id);

}
