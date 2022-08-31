package mainMenu;

import utils.Display;
import register.User;

import java.util.List;

public class MainMenuView {
    public MainMenuView(List<String> menuOptions, List<User> users, int index) {
        System.out.println("Logged as " + users.get(index).getUserName());
        System.out.println("Main menu options:");
        Display.displayList(menuOptions);
        Display.chooseOption();
    }
}
