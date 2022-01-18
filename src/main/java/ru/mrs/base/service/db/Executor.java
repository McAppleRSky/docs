package ru.mrs.base.service.db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.mrs.docs.frontend.OldTableServlet;
import ru.mrs.docs.service.db.dataSet.OldTableColumns;

import java.sql.*;
import java.util.Map;

public class Executor {

    private static final Logger LOGGER = LogManager.getLogger(Executor.class);

    private final Connection connection;

    private final boolean RESULTING_COUNT_NO_RESULT_SET = false,
            RESULTING_RESULT_SET = true;

    public Executor(Connection connection) {
        this.connection = connection;
    }

    public <T> T execQuery(String query, ResultHandler<T> handler) throws SQLException {
        T result = null;
        Boolean execute = null;
        try (Statement stmt = connection.createStatement()) {
            execute = stmt.execute(query) ? RESULTING_RESULT_SET : RESULTING_COUNT_NO_RESULT_SET;
            LOGGER.info(query + "\nreturn " + (execute ? "RESULTING_RESULT_SET" : "RESULTING_COUNT_NO_RESULT_SET"));
            try (ResultSet resultSet = stmt.getResultSet()) {
                result = handler.handle(resultSet);
                ResultSetMetaData metaData = resultSet.getMetaData();
                LOGGER.info("Deleted " + resultSet.rowDeleted() + ", inserted " + resultSet.rowInserted() + "updated" + resultSet.rowUpdated());
            }
        }
        return result;
    }

    public Boolean execUpdate(String queryUpdate) throws SQLException {
        Boolean execute = null;
//        Statement stmt = connection.createStatement();
        try (Statement stmt = connection.createStatement()) {
            execute = stmt.execute(queryUpdate) ? RESULTING_RESULT_SET : RESULTING_COUNT_NO_RESULT_SET;
            LOGGER.info(queryUpdate + "\nreturn " + (execute ? "RESULTING_RESULT_SET" : "RESULTING_COUNT_NO_RESULT_SET"));
        }
//        stmt.close();
        return execute;
    }

}