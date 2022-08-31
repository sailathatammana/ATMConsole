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
        String uniqueName;
        while (true) {
            boolean done;
            uniqueName = readInputFromUser("Enter Unique Name: ", errorMessage("unique name"));
            done = validateUniqueName(uniqueName);
            if (done) {
                break;
            }
        }
        String password = readInputFromUser("Enter password: ", errorMessage("password"));
        String encryptedPassword = auth.encryptPassword(password);
        User user = new User(fullName, uniqueName, encryptedPassword);
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

    private boolean validateUniqueName(String input) {
        for (User userData : users) {
            if ((userData.getUniqueName().equals(input))) {
                System.out.println("Unique name already exists.");
                return false;
            }
        }
        return true;
    }
}
