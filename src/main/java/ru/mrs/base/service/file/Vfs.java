package ru.mrs.base.service.file;

import java.util.Iterator;

public interface Vfs {

    boolean ifExist (String path);
    boolean isDirectory(String path);
    String getAbsolutePath(String file);
    byte[] getBytes(String file);
    String getUTF8Text(String path);
    Iterator<String> getIterator(String startDir);
    boolean mkdir(String path);

    boolean ifExistRoot();
    boolean isDirectoryRoot();
    boolean mkdirRoot();
    Iterator<String> getIteratorRoot();
    boolean remove(String file);

}
