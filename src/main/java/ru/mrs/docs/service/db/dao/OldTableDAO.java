package ru.mrs.docs.service.db.dao;

import ru.mrs.base.service.db.Executor;
import ru.mrs.docs.service.db.dataSet.OldTableDataSet;
import ru.mrs.docs.service.db.dataSet.OldTableColumns;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class OldTableDAO {

    private Executor executor;

    public OldTableDAO(Connection connection) {
        this.executor = new Executor(connection);
    }

    public List<OldTableDataSet> getOldTableList() throws SQLException {
        return executor.execQuery("SELECT * FROM OLD_MAIN_TABLE;", resultSet -> {
            List<OldTableDataSet> oldTableList = new LinkedList<>();
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
