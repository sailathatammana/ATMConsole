package account;

import homeMenu.HomeMenu;
import utils.Display;
import utils.FileHandler;
import utils.User;
import utils.ValidateHelper;

import java.util.List;
import java.util.Scanner;

public class Register {
    Scanner scanner = new Scanner(System.in);
    Encrypt auth = new Encrypt();
    FileHandler fileHandler = new FileHandler();
    ValidateHelper validateHelper = new ValidateHelper();

    private List<User> getAllUsers() {
        return fileHandler.readFromFile();
    }

    public void adduser() {
        List<User> users = getAllUsers();
        Display.clearScreen();
        String fullName = getFullName(users);
        String userName = getUserName(users);
        String encryptedPassword = getPassword();
        users.add(new User(fullName, userName, encryptedPassword));
        fileHandler.writeToFile(users);
        System.out.println("Registered successfully!");
        System.out.print("Press any key and enter to login: ");
        scanner.nextLine();
        new HomeMenu();
    }


    private String getFullName(List<User> users) {
        String fullName;
        while (true) {
            boolean done;
            fullName = readInputFromUser("Enter Full Name: ", Display.errorMessage("full name"));
            done = validateHelper.validateFullName(fullName, users);
            if (done) {
                break;
            }
        }
        return fullName;
    }

    private String getUserName(List<User> users) {
        String userName;
        while (true) {
            boolean done;
            userName = readInputFromUser("Enter User Name: ", Display.errorMessage("user name"));
            done = validateHelper.validateUserName(userName, users);
            if (done) {
                break;
            }
        }
        return userName;
    }

    private String getPassword() {
        String password = readInputFromUser("Enter password: ", Display.errorMessage("password"));
        String encryptedPassword = auth.encryptPassword(password);
        return encryptedPassword;
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
}
