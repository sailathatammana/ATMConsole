package register;

import mainMenu.MainMenu;
import utils.FileHandler;
import utils.User;

import java.util.List;
import java.util.Scanner;

public class Login {
    Scanner scanner = new Scanner(System.in);
    Authentication auth = new Authentication();
    FileHandler fileHandler = new FileHandler();
    private List<User> users;

    private List<User> getAllUsers() {
        return fileHandler.readFromFile();
    }

    public void LoginUser() {
        users = getAllUsers();
        while (true) {
            boolean done = false;
            String uniqueName = readInputFromUser("Enter Unique Name: ", errorMessage("unique name"));
            String password = readInputFromUser("Enter password: ", errorMessage("password"));
            String encryptedPassword = auth.encryptPassword(password);
            done = validateLoginDetails(uniqueName, encryptedPassword);
            if (done) {
                System.out.println("Successfully logged.");
                break;
            } else {
                System.out.println("Invalid Credentials");
            }
        }
        new MainMenu();
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

    private boolean validateLoginDetails(String uniqueName, String encryptedPassword) {
        for (User userData : users) {
            if ((userData.getUniqueName().equals(uniqueName)) && (userData.getPassword().equals(encryptedPassword))) {
               return true;
            }
        }
       return false;
    }
}
