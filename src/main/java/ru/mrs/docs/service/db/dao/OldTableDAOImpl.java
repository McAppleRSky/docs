package ru.mrs.docs.service.db.dao;

import ru.mrs.base.service.db.Executor;
import ru.mrs.docs.service.db.dataSet.OldTableDataSet;
import ru.mrs.docs.service.db.dataSet.OldTableColumns;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class OldTableDAOImpl implements OldTableDAO {

    private Executor executor;

    public OldTableDAOImpl(Connection connection) {
        this.executor = new Executor(connection);
    }

    @Override
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

    @Override
    public Integer createDocsOldTable(Map<String, String[]> parameterMap) throws SQLException {
        String columns = OldTableColumns.URL_SED_INPUT
                + ", " + OldTableColumns.FROM_OWNER
                + ", " + OldTableColumns.WORKER
                + ", " + OldTableColumns.URL_SED_OUTPUT;
        String query = String.format(
                "insert into OLD_MAIN_TABLE (%s) VALUES (%s, %s, %s, %s);",
                columns,
                parameterMap.get(OldTableColumns.URL_SED_INPUT.toString()),
                parameterMap.get(OldTableColumns.FROM_OWNER.toString()),
                parameterMap.get(OldTableColumns.WORKER.toString()),
                parameterMap.get(OldTableColumns.URL_SED_OUTPUT.toString()) );
        return executor.execQuery(query, resultSet -> {
            Integer result = 0;
            return result;
        });
    }

    @Override
    public Integer updateDocsOldTable(Map<String, String[]> parameterMap) {
        return null;
    }
}