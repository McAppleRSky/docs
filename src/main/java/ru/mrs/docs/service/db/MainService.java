package ru.mrs.docs.service.db;

import ru.mrs.docs.service.db.dataSet.MainEntity;

import java.util.List;

public interface MainService {

    List<MainEntity> findAll();

    MainEntity getById(long id);

    long create(MainEntity entity);

    MainEntity update(long id, MainEntity entity);

    void delete(long id);

    void close(long id);

}
