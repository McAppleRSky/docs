package ru.mrs.hamlscript.util;

import java.io.File;

public class FilterFile implements java.io.FileFilter {

    String name, suffix;

    // Только принимает 'pathname' как файл и имеет 'расширение' (extension) это txt.
    @Override
    public boolean accept(File pathname) {

        if (!pathname.isFile()) {
            return false;
        }

        if ( (pathname.getName()==name) && pathname.getAbsolutePath().endsWith(suffix) ) {
            return true;
        }

        return false;
    }

    public FilterFile(String name, String suffix) {
        this.name=name;
        this.suffix=suffix;
    }
}

