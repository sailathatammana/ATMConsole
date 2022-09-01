package register;

import mainMenu.MainMenu;
import utils.FileHandler;

import java.util.List;
import java.util.Scanner;

public class Login {
    Scanner scanner = new Scanner(System.in);
    Authentication auth = new Authentication();
    FileHandler fileHandler = new FileHandler();
    private List<User> users;
    private int index;

    private List<User> getAllUsers() {
        return fileHandler.readFromFile();
    }

    public void LoginUser() {
        users = getAllUsers();
        while (true) {
            boolean done;
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
        new MainMenu(users, index);
    }

    private String readInputFromUser(String message) {
        System.out.print(message);
        String input = scanner.nextLine();
        return input;
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
