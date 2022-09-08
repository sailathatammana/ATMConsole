package menuActions;

import account.Encrypt;
import utils.Display;
import utils.FileHandler;
import utils.User;
import utils.ValidateHelper;

import java.util.List;

public class EditProfile {
    private final List<User> users;
    private final int index;
    Encrypt auth;
    FileHandler fileHandler;
    ValidateHelper validateHelper;

    public EditProfile(List<User> users, int index) {
        this.users = users;
        this.index = index;
        auth = new Encrypt();
        fileHandler = new FileHandler();
        validateHelper = new ValidateHelper();
    }

    public void updateProfile() {
        Display.clearScreen();
        updateUserName();
        updatePassword();
        fileHandler.writeToFile(users);
        Display.returnMainMenu();
    }

    private void updateUserName() {
        String userName;
        System.out.println("user name(Press enter if you do not want to change the user name): ");
        while (true) {
            boolean done;
            userName = validateHelper.readInputFromUser("Enter new User Name: ", errorMessage("user name"), "update");
            done = validateHelper.validateUserName(userName, users);
            if (done) {
                break;
            }
        }
        if (!(userName.equals(""))) {
            users.get(index).setUserName(userName);
            System.out.println("Username has changed successfully!");
        }
    }

    private void updatePassword() {
        System.out.println("Password(Press enter if you do not want to change the password): ");
        String password = validateHelper.readInputFromUser("Enter new password: ", errorMessage("password"), "update");
        if (!(password.equals(""))) {
            String encryptedPassword = auth.encryptPassword(password);
            users.get(index).setPassword(encryptedPassword);
            System.out.println("Password changed successfully!");
        }
    }

    private String errorMessage(String value) {
        String message = "Invalid Entry, " + value + " should not starts with space nor ends with space\nPlease enter valid " + value + ": ";
        return message;
    }
}
