package ru.mrs.base.service.db;

import java.sql.*;
import java.util.Map;

public class Executor {

    private final Connection connection;

    public Executor(Connection connection) {
        this.connection = connection;
    }

    public void execUpdate(String update) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute(update);
        stmt.close();
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