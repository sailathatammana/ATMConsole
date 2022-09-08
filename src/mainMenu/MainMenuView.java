package mainMenu;

import utils.Display;
import utils.User;

import java.util.List;

public class MainMenuView {
    public MainMenuView(List<String> menuOptions, List<User> users, int index) {
        Display.clearScreen();
        System.out.println("Logged as " + users.get(index).getUserName() + "\n");
        System.out.println("Main menu options:");
        Display.displayList(menuOptions);
        Display.chooseOption();
    }
}
