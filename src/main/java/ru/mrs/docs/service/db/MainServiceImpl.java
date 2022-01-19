package ru.mrs.docs.service.db;

import org.apache.commons.lang3.NotImplementedException;
import ru.mrs.docs.service.db.dao.MainDao;
import ru.mrs.docs.service.db.entity.MainEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class MainServiceImpl implements MainService {

    private final String url;
    private final String name;
    private final String pass;

    public MainServiceImpl(String name, String pass, String path) {
        this.url = "jdbc:h2:" + path;
        this.name = name;
        this.pass = pass;
    }

    @Override
    public List<MainEntity> getAll() {
        List<MainEntity> entities = null;
        try(Connection connection = DriverManager.getConnection(url, name, pass)) {
            MainDao dao = new MainDao(connection);
            entities = dao.findAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
//        throw new NotImplementedException("List<MainEntity> findAll need impl");
        return entities;
    }

    @Override
    public MainEntity getById(long id) {
        throw new NotImplementedException("MainEntity getById(long id) need impl");
    }

    @Override
    public void add(MainEntity entity) {
        Boolean queryResult = null;
        try(Connection connection = DriverManager.getConnection(url, name, pass)) {
            MainDao dao = new MainDao(connection);
            queryResult = dao.saveNew(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        throw new NotImplementedException("long create(MainEntity entity) need impl");
    }

    @Override
    public void update(MainEntity entity) {
        Boolean queryResult = null;
        try(Connection connection = DriverManager.getConnection(url, name, pass)) {
            MainDao dao = new MainDao(connection);
            queryResult = dao.merge(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        throw new NotImplementedException("MainEntity update(long id, MainEntity entity) need impl");
    }

    @Override
    public void remove(long id) {
        throw new NotImplementedException("void delete(long id) need impl");
    }

}
