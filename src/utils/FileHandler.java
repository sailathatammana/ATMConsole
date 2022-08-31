package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {
    File data = new File("data.txt");

    public void writeToFile(User user) {
        try {
            FileWriter fw = new FileWriter(data, true);
            PrintWriter writer = new PrintWriter(fw);
            writer.println(user.getFullName() + "," + user.getUserName() + "," + user.getPassword() + "," + user.getBalance());
            writer.flush();
            fw.flush();
            writer.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public List<User> readFromFile() {
        List<User> users = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(data);
            while (scanner.hasNextLine()) {
                List<String> usersList = List.of(scanner.nextLine().split(","));
                String fullName = usersList.get(0);
                String userName = usersList.get(1);
                String password = usersList.get(2);
                User user = new User(fullName, userName, password);
                users.add(user);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return users;
    }
}
