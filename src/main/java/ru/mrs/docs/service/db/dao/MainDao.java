package ru.mrs.docs.service.db.dao;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.mrs.base.service.db.Executor;
import ru.mrs.docs.service.db.entity.MainColumns;
import ru.mrs.docs.service.db.entity.MainEntity;
import ru.mrs.docs.service.db.entity.MainEntityMapColumnValueStringFacade;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MainDao implements DaoOperations<MainEntity, Long> {

    private static final Logger LOGGER = LogManager.getLogger(MainDao.class);
    private Executor executor;

    public MainDao(Connection connection) {
        this.executor = new Executor(connection);
    }

    @Override
    public List<MainEntity> findAll() {
        return executor.execFetch("SELECT * FROM main;", resultSet -> {
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
        Map<MainColumns, String> mapEntity = new MainEntityMapColumnValueStringFacade(entity);
        String queryTemplate = "INSERT INTO main(" +
                "%s, " +
                "%s, " +
//                "%s, " +
                "%s, " +
//                "%s, " +
                "%s, " +
//                "%s, " +
                "%s, " +
                "%s, " +
//                "%s, " +
//                "%s, " +
//                "%s, " +
                "%s, " +
                "%s, " +
                "%s" +
                ") \n\tVALUES (" +
                "%s, " +
                "%s, " +
//                "%s, " +
                "%s, " +
//                "%s, " +
                "%s, " +
//                "%s, " +
                "%s, " +
                "%s, " +
//                "%s, " +
//                "%s, " +
//                "%s, " +
                "%s, " +
                "%s, " +
                "%s);" ;
        String queryCreate = String.format(
                queryTemplate,
                MainColumns.URL_INPUT,
                MainColumns.GEN_ORG_NUMB,
//                MainColumns.GEN_ORG_DATE,
                MainColumns.OUTPUT_NUMB,
//                MainColumns.OUTPUT_DATE,
                MainColumns.FROM_OWNER,
//                MainColumns.INPUT_DATE,
                MainColumns.INPUT_NUMB,
                MainColumns.WORKER,
//                MainColumns.HAND_PASS,
//                MainColumns.ANSWER_COMP,
//                MainColumns.ANSWER_DATE,
                MainColumns.ANSWER_NUMB,
                MainColumns.URL_OUTPUT,
                MainColumns.NOTE
                ,
                mapEntity.get(MainColumns.URL_INPUT),
                mapEntity.get(MainColumns.GEN_ORG_NUMB),
//                mapEntity.get(MainColumns.GEN_ORG_DATE),
                mapEntity.get(MainColumns.OUTPUT_NUMB),
//                mapEntity.get(MainColumns.OUTPUT_DATE),
                mapEntity.get(MainColumns.FROM_OWNER),
//                mapEntity.get(MainColumns.INPUT_DATE),
                mapEntity.get(MainColumns.INPUT_NUMB),
                mapEntity.get(MainColumns.WORKER),
//                mapEntity.get(MainColumns.HAND_PASS),
//                mapEntity.get(MainColumns.ANSWER_COMP),
//                mapEntity.get(MainColumns.ANSWER_DATE),
                mapEntity.get(MainColumns.ANSWER_NUMB),
                mapEntity.get(MainColumns.URL_OUTPUT),
                mapEntity.get(MainColumns.NOTE)
        );
        LOGGER.info(queryCreate);
        queryCreate = queryCreate
                .replace("\n","")
                .replace("\t", "");
        return executor.execChange(queryCreate);
    }

    @Override
    public boolean merge(MainEntity entity) {
        Map<MainColumns, String> mapEntity = new MainEntityMapColumnValueStringFacade(entity);
        String queryTemplate = "UPDATE main set \t" +
                "%s=%s, \n\t" +
                "%s=%s, \n\t\t" +
//                "%s=%s, \n\t\t" +
                "%s=%s, \n\t\t" +
//                "%s=%s, \n\t\t" +
                "%s=%s, \n\t\t" +
//                "%s=%s, \n\t\t" +
                "%s=%s, \n\t\t" +
                "%s=%s, \n\t\t" +
//                "%s=%s, \n\t\t" +
//                "%s=%s, \n\t\t" +
//                "%s=%s, \n\t\t" +
                "%s=%s, \n\t\t" +
                "%s=%s, \n\t\t" +
                "%s=%s \n\t\t" +
                "WHERE %s=%s;" ;
        String queryUpdate = String.format(
                queryTemplate,
                MainColumns.URL_INPUT, mapEntity.get(MainColumns.URL_INPUT),
                MainColumns.GEN_ORG_NUMB, mapEntity.get(MainColumns.GEN_ORG_NUMB),
//                MainColumns.GEN_ORG_DATE, mapEntity.get(MainColumns.GEN_ORG_DATE),
                MainColumns.OUTPUT_NUMB, mapEntity.get(MainColumns.OUTPUT_NUMB),
//                MainColumns.OUTPUT_DATE, mapEntity.get(MainColumns.OUTPUT_DATE),
                MainColumns.FROM_OWNER, mapEntity.get(MainColumns.FROM_OWNER),
//                MainColumns.INPUT_DATE, mapEntity.get(MainColumns.INPUT_DATE),
                MainColumns.INPUT_NUMB, mapEntity.get(MainColumns.INPUT_NUMB),
                MainColumns.WORKER, mapEntity.get(MainColumns.WORKER),
//                MainColumns.HAND_PASS, mapEntity.get(MainColumns.HAND_PASS),
//                MainColumns.ANSWER_COMP, mapEntity.get(MainColumns.ANSWER_COMP),
//                MainColumns.ANSWER_DATE, mapEntity.get(MainColumns.ANSWER_DATE),
                MainColumns.ANSWER_NUMB, mapEntity.get(MainColumns.ANSWER_NUMB),
                MainColumns.URL_OUTPUT, mapEntity.get(MainColumns.URL_OUTPUT),
                MainColumns.NOTE, mapEntity.get(MainColumns.NOTE),
                MainColumns.ID, mapEntity.get(MainColumns.ID)
                );
        LOGGER.info(queryUpdate);
        queryUpdate = queryUpdate
                .replace("\n","")
                .replace("\t", "");
        return executor.execChange(queryUpdate);
    }

    @Override
    public boolean deleteById(Long id) {
        throw new NotImplementedException("boolean delete(Long id) need impl");
//        return null;
    }

}