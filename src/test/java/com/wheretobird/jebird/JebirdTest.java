package com.wheretobird.jebird;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import io.github.cdimascio.dotenv.Dotenv;

public abstract class JebirdTest {
    /*
     * Parent class to centralize api token
     * retrieval. A valid api token under the
     * key EBIRD_API_KEY must be available in
     * a .env file in the project root.
     */

    protected String apiToken = null;

    public JebirdTest() {
        try {
            Dotenv dotenv = Dotenv.load();
            this.apiToken = dotenv.get("EBIRD_API_KEY");
        } catch (Exception e) {
            System.out.println("Failed to find dotenv file, trying environment instead");
            this.apiToken = System.getenv("EBIRD_API_KEY");
        }
    }

    /**
     * Writes an object to a file and instantiates a new
     * object from that file. Used to test serialization.
     * 
     * @param <T> Object to write and return
     * @param obj Instance to write and return
     * @return an instance created from file stream
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public <T extends Object> T serialize_deserialize(T obj) throws IOException, ClassNotFoundException {
        String fileName = "test.txt";
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(obj);
        objectOutputStream.flush();
        objectOutputStream.close();

        FileInputStream fileInputStream = new FileInputStream(fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        T objFromStream = (T) objectInputStream.readObject();
        objectInputStream.close();
        File testFile = new File(fileName);
        if (testFile.delete()) {
            System.out.println("Deleted the file: " + testFile.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
        return objFromStream;

    }
}
