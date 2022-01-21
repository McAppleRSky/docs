package ru.mrs.base.service.file;

import org.apache.commons.lang3.NotImplementedException;

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
        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
            BufferedInputStream bufferedInputStreamout = new BufferedInputStream(fileInputStream);
            ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStreamout);
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void writeResource(Object object, String resource) {
        throw new NotImplementedException("void writeResource(Object object, String resource)");
    }

    public static Object readResource(String resource) {
        try (InputStream inputStream = ObjectWriter.class.getClassLoader().getResourceAsStream(resource)) {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}