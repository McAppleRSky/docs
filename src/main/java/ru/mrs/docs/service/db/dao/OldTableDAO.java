package ru.mrs.docs.service.db.dao;

import ru.mrs.base.service.db.Executor;
import ru.mrs.docs.service.db.dataSet.OldTableDataSet;

import java.sql.Connection;
import java.sql.SQLException;

public class OldTableDAO {

    private Executor executor;

    public OldTableDAO(Connection connection) {
        this.executor = new Executor(connection);
    }

    public OldTableDataSet get() throws SQLException {
        return executor.execQuery("SELECT * FROM OLD_MAIN_TABLE;", result -> {
            result.next();
            return new OldTableDataSet()
                    .setId(
                            result.getInt(1) )
                    .setUrlSedInput(
                            result.getString(2) )
                    .setFromOwner(
                            result.getString(7) )
                    .setWorker(
                            result.getString(10) )
                    .setUrlSedOutput(
                            result.getString(15) );
        });
    }

    public Object docs() throws SQLException {
        return executor.execQuery("SELECT * FROM OLD_MAIN_TABLE;", result -> {
            result.next();
            return new OldTableDataSet()
                    .setId(
                            result.getInt(1) )
                    .setUrlSedInput(
                            result.getString(2) )
                    .setFromOwner(
                            result.getString(7) )
                    .setWorker(
                            result.getString(10) )
                    .setUrlSedOutput(
                            result.getString(15) );
        });
    }
}
