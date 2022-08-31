package register;

import utils.FileHandler;
import utils.User;

import java.util.List;
import java.util.Scanner;

public class Register {
    Scanner scanner = new Scanner(System.in);
    Authentication auth = new Authentication();
    FileHandler fileHandler = new FileHandler();
    private List<User> users;

    private List<User> getAllUsers() {
        return fileHandler.readFromFile();
    }

    public void adduser() {
        users = getAllUsers();
        String fullName;
        while (true) {
            boolean done;
            fullName = readInputFromUser("Enter Full Name: ", errorMessage("full name"));
            done = validateFullName(fullName);
            if (done) {
                break;
            }
        }
        String userName;
        while (true) {
            boolean done;
            userName = readInputFromUser("Enter User Name: ", errorMessage("user name"));
            done = validateUserName(userName);
            if (done) {
                break;
            }
        }
        String password = readInputFromUser("Enter password: ", errorMessage("password"));
        String encryptedPassword = auth.encryptPassword(password);
        User user = new User(fullName, userName, encryptedPassword);
        fileHandler.writeToFile(user);
    }

    private String errorMessage(String value) {
        String message = "Invalid Entry, " + value + " should not be empty or starts with space\nPlease enter valid " + value + ": ";
        return message;
    }

    private String readInputFromUser(String message, String errorMessage) {
        System.out.print(message);
        String input = scanner.nextLine();
        while ((input.equals("") || (input.startsWith(" ")))) {
            System.out.print(errorMessage);
            input = scanner.nextLine();
            input.trim();
        }
        return input;
    }

    private boolean validateFullName(String input) {
        for (User userData : users) {
            if ((userData.getFullName().equals(input))) {
                System.out.println("Full name already exists.");
                return false;
            }
        }
        return true;
    }

    private boolean validateUserName(String input) {
        for (User userData : users) {
            if ((userData.getUserName().equals(input))) {
                System.out.println("User name already exists.");
                return false;
            }
        }
        return true;
    }
}
