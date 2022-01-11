package ru.mrs.docs.service;

import org.apache.commons.lang3.NotImplementedException;
import ru.mrs.base.service.file.*;

import java.io.File;
import java.util.Iterator;

public class UserProfileTest {

    void createTest() {
        String login = "admin", pass = "admin", email = "mcapple@yandex.ru";
        UserProfile userProfile = new UserProfile(login, pass, email);
//        String current = "./";
//        String path = //UserProfile.class.getClassLoader().getResource(".").getPath();
        String rootPath = "serial";
        VFS vfs = new VfsTestImpl(rootPath);
        if (vfs.ifExistRoot()) {
            if (!vfs.isDirectoryRoot()){
                throw new IllegalArgumentException("Root is file! Require directory");
            } else {
                System.out.print(""); // use exist
            }
        } else {
            if (vfs.mkdirRoot()) {
                System.out.print(""); // use created
            } else {
                throw new IllegalArgumentException("something wrong");
            }
        }

        Iterator<String> iteratorRoot = vfs.getIteratorRoot();
//        while (iteratorRoot.hasNext()) { String current = iteratorRoot.next(); }
        if (iteratorRoot.hasNext()) {
            String current = iteratorRoot.next();
            if (vfs.isDirectory(current)) {
                ObjectWriter.write(userProfile, current + "/profile.ser");
                System.out.print("");
            } else {
                throw new IllegalArgumentException("Root is file");
            }
        } else {
            throw new IllegalArgumentException("Root is absent");
        }

        ObjectWriter.write(userProfile, "path");

        Object userProfileObject = ReflectionHelper.createInstance(UserProfile.class.getCanonicalName());

        ReflectionHelper.setFieldValue(userProfileObject, "login", login);
        ReflectionHelper.setFieldValue(userProfileObject, "pass", pass);
        ReflectionHelper.setFieldValue(userProfileObject, "email", email);

    }

}

class VfsTestImpl extends VfsAbstract implements VFS {

    public VfsTestImpl(String root) {
        super(root);
    }

    @Override
    public boolean ifExist(String path) {
//        throw new NotImplementedException("");
        return new File(path).exists();
    }

    @Override
    public boolean isDirectory(String path) {
//        throw new NotImplementedException("");
        return new File(path).isDirectory();
    }

    @Override
    public String getAbsolutePath(String file) {
//        throw new NotImplementedException("");
        return new File(file).getAbsolutePath();
    }

    @Override
    public byte[] getBytes(String file) {
        throw new NotImplementedException("");
    }

    @Override
    public String getUTF8Text(String path) {
        throw new NotImplementedException("");
    }

    @Override
    public Iterator<String> getIterator(String startDir) {
//        throw new NotImplementedException("");
        System.out.println();
        return new FileIterator(startDir);
    }

    @Override
    public boolean mkdir(String file) {
//        throw new NotImplementedException("");
        return new File(file).mkdir();
    }

    @Override
    public boolean remove(String file) {
        return new File(file).delete();
    }

    @Override
    public boolean ifExistRoot() {
        return ifExist(getRoot());
    }

    @Override
    public boolean isDirectoryRoot() {
        return isDirectory(getRoot());
    }

    @Override
    public boolean mkdirRoot() {
        return mkdir(getRoot());
    }

    @Override
    public Iterator<String>  getIteratorRoot() {
        return getIterator(getRoot());
    }

}
