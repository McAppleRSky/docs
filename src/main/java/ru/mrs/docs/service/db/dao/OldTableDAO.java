package ru.mrs.docs.service.db.dao;

import ru.mrs.base.service.db.Executor;
import ru.mrs.docs.service.db.dataSet.OldTableDataSet;
import ru.mrs.docs.service.db.dataSet.OldTableColumns;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OldTableDAO {

    private Executor executor;

    public OldTableDAO(Connection connection) {
        this.executor = new Executor(connection);
    }

    /*public OldTableDataSet get() throws SQLException {
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
    }*/

    public List<OldTableDataSet> getOldTableList() throws SQLException {
        return executor.execQuery("SELECT * FROM OLD_MAIN_TABLE;", resultSet -> {
            List<OldTableDataSet> oldTableList = new ArrayList<>();
            while (resultSet.next()){
                oldTableList.add(
                        new OldTableDataSet()
                        .setId(
                                resultSet.getInt(
                                        OldTableColumns.ID.toString() ) )
                        .setUrlIn(
                                resultSet.getString(
                                        OldTableColumns.URL_SED_INPUT.toString() ) )
                        .setFromOwner(
                                resultSet.getString(
                                        OldTableColumns.FROM_OWNER.toString() ) )
                        .setWorker(
                                resultSet.getString(
                                        OldTableColumns.WORKER.toString() ) )
                        .setUrlOut(
                                resultSet.getString(
                                        OldTableColumns.URL_SED_OUTPUT.toString() ) ) );
            }

            return oldTableList;
        });
    }
}
