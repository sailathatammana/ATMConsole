package register;

import mainMenu.MainMenu;
import utils.Display;
import utils.FileHandler;

import java.util.List;

public class Login {
    Authentication auth = new Authentication();
    FileHandler fileHandler = new FileHandler();
    private List<User> users;
    private int index;

    private List<User> getAllUsers() {
        return fileHandler.readFromFile();
    }

    public void LoginUser() {
        users = getAllUsers();
        Display.clearScreen();
        while (true) {
            boolean done;
            String userName = Display.readInputFromUser("Enter user Name: ");
            String password = Display.readInputFromUser("Enter password: ");
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
