package ru.mrs.docs.service.db;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.mrs.base.service.file.Vfs;
import ru.mrs.docs.Embedded;
import ru.mrs.docs.PropertyKeys;
import ru.mrs.docs.service.db.dao.MainDao;
import ru.mrs.docs.service.db.entity.IMainEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class MainServiceImpl implements MainService {

    private static final Logger LOGGER = LogManager.getLogger(MainDao.class);

    private final Map context;

    private final Vfs VFS;

    private final String url;

    private final String name;

    private final String pass;

    public MainServiceImpl(Map context) {
        this.context = context;
        this.name = context.get(PropertyKeys.DB_USR_NAME).toString();
        this.pass = context.get(PropertyKeys.DB_USR_PASSWORD).toString();
        VFS = (Vfs) context.get(Vfs.class);
        String absolutePath = VFS.getAbsolutePath(context.get(PropertyKeys.DB_DATA_PATH).toString());
        this.url = "jdbc:h2:" + absolutePath + "/h2";
        LOGGER.info(url + " " + this.name + " " + this.pass + " ");

    }

    @Override
    public List<IMainEntity> getAll() {
        List<IMainEntity> entities = null;
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
    public IMainEntity getById(long id) {
        throw new NotImplementedException("MainEntity getById(long id) need impl");
    }

    @Override
    public void add(IMainEntity entity) {
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
    public void update(IMainEntity entity) {
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
