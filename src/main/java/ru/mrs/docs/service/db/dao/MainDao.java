package ru.mrs.docs.service.db.dao;

import org.apache.commons.lang3.NotImplementedException;
import ru.mrs.base.service.db.Executor;
import ru.mrs.docs.service.db.entity.MainColumns;
import ru.mrs.docs.service.db.entity.MainEntity;
import ru.mrs.docs.service.db.entity.MainEntityMapColumnValueStringFacade;
import ru.mrs.docs.service.db.entity.MainHelper;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MainDao implements DaoOperations<MainEntity, Long> {

    private Executor executor;

    public MainDao(Connection connection) {
        this.executor = new Executor(connection);
    }

    @Override
    public List<MainEntity> findAll() {
        return executor.execQuery("SELECT * FROM main;", resultSet -> {
            List<MainEntity> entities = new LinkedList<>();
            while (resultSet.next()) {
                entities.add(
                        new MainEntity()
                                .setId(
                                        resultSet.getLong(
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
                                                MainColumns.URL_OUTPUT.toString() ) )
                                .setUrlOutput(
                                        resultSet.getString(
                                                MainColumns.NOTE.toString() ) ) );
            }
            return entities;
        });
    }

    @Override
    public Optional<MainEntity> findById(Long id) {
        throw new NotImplementedException("Optional<MainEntity> findById(Long id) need impl");
//        return null;
    }

    @Override
    public boolean saveNew(MainEntity entity) {
        throw new NotImplementedException("boolean save(MainEntity entity) need impl");
//        return false;
    }

    @Override
    public boolean merge(MainEntity entity) {
        String queryTemplate = "UPDATE main \n\t" +
                "set %s=%s,\n\t" +
                "WHERE %s=%s;" ;

        Map<MainColumns, String> mapEntity = new MainEntityMapColumnValueStringFacade(entity);
        executor.execUpdate("UPDATE main set WHERE ;");
//        throw new NotImplementedException("boolean update(MainEntity entity) need impl");
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        throw new NotImplementedException("boolean delete(Long id) need impl");
//        return null;
    }

}
