package ru.mrs.docs.service.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import ru.mrs.docs.service.db.dao.OldTableDAOImpl;
import ru.mrs.docs.service.db.entity.OldTableColumns;
import ru.mrs.docs.service.db.entity.OldTableDataSet;

public class DBServiceImpl implements DBService {

//    private Connection connection = null;
    private final String url;
    private final String name;
    private final String pass;

    public DBServiceImpl(String name, String pass, String path) {
        this.url = "jdbc:h2:file:" + path;
        this.name = name;
        this.pass = pass;
//        this.connection = DriverManager.getConnection("jdbc:h2:file:" + path, name, pass);
    }

    @Override
    public List<OldTableDataSet> allDocs() {
        List<OldTableDataSet> oldTableList = null;
        try(Connection connection = DriverManager.getConnection(url, name, pass)){
            OldTableDAOImpl oldTableDAO = new OldTableDAOImpl(connection);
            oldTableList = oldTableDAO.getOldTableList();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return oldTableList;
    }

    @Override
    public int createDocsOldTable(Map<OldTableColumns, String> parameterMap) {
        Integer result = null;
        try(Connection connection = DriverManager.getConnection(url, name, pass)){
            result = new OldTableDAOImpl(connection).createDocsOldTable(parameterMap);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateOldTable(Map<OldTableColumns, String> parameterMap) {
        Integer result = null;
        try(Connection connection = DriverManager.getConnection(url, name, pass)){
            result = new OldTableDAOImpl(connection).updateDocsOldTable(parameterMap);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

}
