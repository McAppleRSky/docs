package ru.mrs.docs.service.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import ru.mrs.docs.service.db.dao.OldTableDAO;
import ru.mrs.docs.service.db.dataSet.OldTableDataSet;

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
    public List<OldTableDataSet> allDocuments() {
        List<OldTableDataSet> oldTableList = null;
        try(Connection connection = DriverManager.getConnection(url, name, pass)){
            OldTableDAO oldTableDAO = new OldTableDAO(connection);
            oldTableList = oldTableDAO.getOldTableList();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return oldTableList;
    }

}
