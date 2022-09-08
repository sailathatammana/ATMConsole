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
            fullName = validateHelper.readInputFromUser("Enter Full Name: ", Display.errorMessage("full name"),"register");
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
            userName = validateHelper.readInputFromUser("Enter User Name: ", Display.errorMessage("user name"),"register");
            done = validateHelper.validateUserName(userName, users);
            if (done) {
                break;
            }
        }
        return userName;
    }

    private String getPassword() {
        String password = validateHelper.readInputFromUser("Enter password: ", Display.errorMessage("password"), "register");
        String encryptedPassword = auth.encryptPassword(password);
        return encryptedPassword;
    }

}
