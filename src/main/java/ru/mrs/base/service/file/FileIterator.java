package ru.mrs.base.service.file;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class FileIterator implements Iterator<String> {

    private Queue<File> files = new LinkedList<>();
//    String root, path;

    public FileIterator(String startDir //Queue<File> files
    )
    {
        File file = new File(startDir);
        this.files.add(file);
    }

    @Override
    public boolean hasNext() {
        return !files.isEmpty();
    }

    @Override
    public String next() {
        File file = files.peek();
        if (file.isDirectory()) {
            for (File subFile : file.listFiles()) {
                files.add(subFile);
            }
        }
        return files.poll().getAbsolutePath();
    }

}
