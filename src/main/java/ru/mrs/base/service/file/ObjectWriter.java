package ru.mrs.base.service.file;

import java.io.*;

public class ObjectWriter {

    public static void write(Object object, String fileName) {
        try (FileOutputStream out = new FileOutputStream(fileName)) {
            BufferedOutputStream bout = new BufferedOutputStream(out);
            ObjectOutputStream dout = new ObjectOutputStream(bout);
            dout.writeObject(object);
            dout.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object read(String fileName) {
        try (FileInputStream out = new FileInputStream(fileName)) {
            BufferedInputStream bout = new BufferedInputStream(out);
            ObjectInputStream dout = new ObjectInputStream(bout);
            return dout.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}