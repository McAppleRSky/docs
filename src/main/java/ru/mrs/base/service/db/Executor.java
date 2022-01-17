package ru.mrs.base.service.db;

import ru.mrs.docs.service.db.dataSet.OldTableColumns;

import java.sql.*;
import java.util.Map;

public class Executor {

    private final Connection connection;

    public Executor(Connection connection) {
        this.connection = connection;
    }

    public <T> T execQuery(String query, ResultHandler<T> handler) throws SQLException {
        T result = null;
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(query);
            try (ResultSet resultSet = stmt.getResultSet()) {
                result = handler.handle(resultSet);
            }
        }
        return result;
    }

    public void execUpdate(String update) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute(update);
        stmt.close();
    }

    public void execCreateDocsOldTables(Map<String, String> paramToValue) {
        String columns = new StringBuilder(OldTableColumns.URL_SED_INPUT.toString())
                .append(", ")
                .append(OldTableColumns.FROM_OWNER)
                .append(", ")
                .append(OldTableColumns.WORKER)
                .append(", ")
                .append(OldTableColumns.URL_SED_OUTPUT).toString();
        String create = "insert into OLD_MAIN_TABLE ("+columns+") VALUES (?, ?, ?, ?);";
        try(PreparedStatement preparedStatement = connection.prepareStatement(create)) {
            for (String param : paramToValue.keySet()) {
                /*preparedStatement.setString(1, id);
                preparedStatement.setString(2, idToName.get(id));*/
                preparedStatement.executeUpdate(create);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void execUpdate(Map<Integer, String> idToName) {
        String update = "insert into ...?, ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(update)) {
            for (Integer id : idToName.keySet()) {
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, idToName.get(id));
                preparedStatement.executeUpdate(update);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}