package menuActions;

import register.Authentication;
import register.User;
import utils.FileHandler;

import java.util.List;
import java.util.Scanner;

public class EditProfile {
    private final List<User> users;
    private final int index;
    Authentication auth;
    FileHandler fileHandler;
    private final Scanner scanner;

    public EditProfile(List<User> users, int index) {
        this.users = users;
        this.index = index;
        scanner = new Scanner(System.in);
        auth = new Authentication();
        fileHandler = new FileHandler();
    }

    public void updateProfile() {
        String userName;
        while (true) {
            boolean done;
            System.out.println("user name(Press enter if you do not want to change the user name): ");
            userName = readInputFromUser("Enter User Name: ", errorMessage("user name"));
            done = validateUserName(userName);
            if (done) {
                break;
            }
        }
        if (!(userName.trim().equals(""))) {
            users.get(index).setUserName(userName);
            System.out.println("Username has changed successfully!");
        }
        System.out.println("Password(Press enter if you do not want to change the password): ");
        String password = readInputFromUser("Enter password: ", errorMessage("password"));
        if (!(password.trim().equals(""))) {
            String encryptedPassword = auth.encryptPassword(password);
            users.get(index).setPassword(encryptedPassword);
            System.out.println("Password changed successfully!");
        }
        fileHandler.writeToFile(users);
        System.exit(1);
    }

    private String errorMessage(String value) {
        String message = "Invalid Entry, " + value + " should not start with space\nPlease enter valid " + value + ": ";
        return message;
    }

    private String readInputFromUser(String message, String errorMessage) {
        System.out.print(message);
        String input = scanner.nextLine();
        while (((input.startsWith(" ")))) {
            System.out.print(errorMessage);
            input = scanner.nextLine();
            input.trim();
        }
        return input;
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