package register;

import homeMenu.HomeMenu;
import utils.Display;
import utils.FileHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Register {
    Scanner scanner = new Scanner(System.in);
    Authentication auth = new Authentication();
    FileHandler fileHandler = new FileHandler();
    private List<User> user = new ArrayList<>();

    private List<User> getAllUsers() {
        return fileHandler.readFromFile();
    }

    public void adduser() {
        user = getAllUsers();
        String fullName;
        while (true) {
            boolean done;
            fullName = readInputFromUser("Enter Full Name: ", Display.errorMessage("full name"));
            done = validateFullName(fullName);
            if (done) {
                break;
            }
        }
        String userName;
        while (true) {
            boolean done;
            userName = readInputFromUser("Enter User Name: ", Display.errorMessage("user name"));
            done = validateUserName(userName);
            if (done) {
                break;
            }
        }
        String password = readInputFromUser("Enter password: ", Display.errorMessage("password"));
        String encryptedPassword = auth.encryptPassword(password);
        user.add(new User(fullName, userName, encryptedPassword));
        fileHandler.writeToFile(user);
        System.out.println("Registered successfully!");
        System.out.print("Press any key and enter to login: ");
        scanner.nextLine();
        new HomeMenu();
    }

    private String readInputFromUser(String message, String errorMessage) {
        System.out.print(message);
        String input = scanner.nextLine();
        while ((input.equals("") || (input.startsWith(" ")) || (input.endsWith(" ")))) {
            System.out.print(errorMessage);
            input = scanner.nextLine();
        }
        return input;
    }

    private boolean validateFullName(String input) {
        for (User userData : user) {
            if ((userData.getFullName().equals(input))) {
                System.out.println("Full name already exists.");
                return false;
            }
        }
        return true;
    }

    private boolean validateUserName(String input) {
        for (User userData : user) {
            if ((userData.getUserName().equals(input))) {
                System.out.println("User name already exists.");
                return false;
            }
        }
        return true;
    }
}
