package ru.mrs.hamlscript.util;

import java.io.File;
import java.io.FileFilter;

public class FilterChild implements FileFilter {

    String name;

    // Только принимает 'pathname' как файл и имеет 'расширение' (extension) это txt.
    @Override
    public boolean accept(File pathname) {

        if (!pathname.isDirectory()) {
            return false;
        }

        if (pathname.getName()==name) {
            return true;
        }

        return false;
    }

    public FilterChild(String name) {
        this.name=name;
    }
}

