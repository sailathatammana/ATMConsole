package utils;

import register.User;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private List<User> users = new ArrayList<>();
    private final String fileName = "data.txt";

    public void writeToFile(List<User> user) {
        try {
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(user);
            output.close();
            file.close();
        } catch (IOException e) {
            System.out.println("File doesn't found" + e);
        }
    }

    public List<User> readFromFile() {
        try {
            Files.isReadable(Paths.get("data.txt"));
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream stream = new ObjectInputStream(file);
            users = (List<User>) stream.readObject();
            stream.close();
            file.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }
}
