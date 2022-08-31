package mainMenu;

import utils.Display;
import utils.User;

import java.util.List;

public class MainMenuView {
    public MainMenuView(List<String> menuOptions, User user) {
        System.out.println("Logged as "+ user.getUserName());
        System.out.println("Main menu options:");
        Display.displayList(menuOptions);
        Display.chooseOption();
    }
}
