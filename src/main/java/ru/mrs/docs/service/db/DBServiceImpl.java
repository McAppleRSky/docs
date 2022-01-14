package ru.mrs.docs.service.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.h2.jdbcx.JdbcDataSource;

public class DBServiceImpl implements DBService {

    private Connection connection = null;
    private final String url;
    private final String name;
    private final String pass;

    public DBServiceImpl(String name, String pass, String path) {
        this.url = "jdbc:h2:file:" + path;
        this.name = name;
        this.pass = pass;
//        this.connection = DriverManager.getConnection("jdbc:h2:file:" + path, name, pass);
    }

    public UserProfile getOldTable(String login) throws DBException {

    }

}
