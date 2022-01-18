package ru.mrs.docs.service.db.dao;

import ru.mrs.base.service.db.Executor;
import ru.mrs.docs.service.db.dataSet.MainColumns;
import ru.mrs.docs.service.db.dataSet.MainEntity;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

public class MainDao implements CrudOperations<MainEntity, Long> {

    private Executor executor;

    public MainDao(Connection connection) {
        this.executor = new Executor(connection);
    }

    @Override
    public List<MainEntity> findAll() {
        return executor.execQuery("SELECT * FROM main;", resultSet -> {
            List<MainEntity> entities = new LinkedList<>();
            while (resultSet.next()){
                entities.add(
                        new MainEntity()
                                .setId(
                                        resultSet.getInt(
                                                MainColumns.ID.toString() ) )
                                .setUrlInput(
                                        resultSet.getString(
                                                MainColumns.URL_INPUT.toString() ) )
                                .setFromOwner(
                                        resultSet.getString(
                                                MainColumns.FROM_OWNER.toString() ) )
                                .setWorker(
                                        resultSet.getString(
                                                MainColumns.WORKER.toString() ) )
                                .setUrlOutput(
                                        resultSet.getString(
                                                MainColumns.URL_OUTPUT.toString() ) ) );
            }
            return entities;
        });
    }

    @Override
    public MainEntity findById(Long id) {
        return null;
    }

    @Override
    public MainEntity update(MainEntity entity) {
        return null;
    }

    @Override
    public MainEntity delete(Long id) {
        return null;
    }
}
