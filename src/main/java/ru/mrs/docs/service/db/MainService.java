package ru.mrs.docs.service.db;

import ru.mrs.docs.service.db.entity.IMainEntity;

import java.util.List;

public interface MainService {

    List<IMainEntity> getAll();

    IMainEntity getById(long id);

    void add(IMainEntity entity);

    void update(IMainEntity entity);

    void remove(long id);

}
