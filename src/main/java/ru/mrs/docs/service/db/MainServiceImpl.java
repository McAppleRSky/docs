package ru.mrs.docs.service.db;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.mrs.base.service.file.Vfs;
import ru.mrs.docs.Embedded;
import ru.mrs.docs.service.db.dao.MainDao;
import ru.mrs.docs.service.db.entity.MainEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class MainServiceImpl implements MainService {

    private static final Logger LOGGER = LogManager.getLogger(MainDao.class);
    private static final Vfs VFS = (Vfs) Embedded.context.get(Vfs.class);

    private final String url;
    private final String name;
    private final String pass;

    public MainServiceImpl(Object name, Object pass, Object path) {
        String absolutePath = VFS.getAbsolutePath(path.toString());
        this.url = "jdbc:h2:" + absolutePath + "/h2";
        this.name = name.toString();
        this.pass = pass.toString();
        LOGGER.info(url + " " + this.name + " " + this.pass + " ");
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
