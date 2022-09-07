package account;

import homeMenu.HomeMenu;
import mainMenu.MainMenu;
import utils.Display;
import utils.FileHandler;
import utils.User;

import java.util.List;
import java.util.Scanner;

public class Login {
    Encrypt auth = new Encrypt();
    FileHandler fileHandler = new FileHandler();
    private List<User> users;
    private int index;
    Scanner scanner = new Scanner(System.in);

    private List<User> getAllUsers() {
        return fileHandler.readFromFile();
    }

    public void LoginUser() {
        users = getAllUsers();
        Display.clearScreen();
        while (true) {
            boolean done;
            if (listHasUsers()) break;
            String userName = readInputFromUser("Enter user Name: ");
            String password = readInputFromUser("Enter password: ");
            String encryptedPassword = auth.encryptPassword(password);
            done = validateLoginDetails(userName, encryptedPassword);
            if (done) {
                break;
            } else {
                System.out.println("Invalid Credentials");
            }
        }
        while (true) {
            new MainMenu(users, index);
        }
    }

    private boolean listHasUsers() {
        if (users.size() == 0) {
            System.out.println("No register user available to login");
            Display.returnMainMenu();
            new HomeMenu();
            return true;
        }
        return false;
    }

    private String readInputFromUser(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    private boolean validateLoginDetails(String userName, String encryptedPassword) {
        for (User userData : users) {
            if ((userData.getUserName().equals(userName)) && (userData.getPassword().equals(encryptedPassword))) {
                index = users.indexOf(userData);
                return true;
            }
        }
        return false;
    }
}
