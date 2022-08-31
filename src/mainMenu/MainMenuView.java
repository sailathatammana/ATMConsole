package mainMenu;

import utils.Display;

import java.util.List;

public class MainMenuView {
    public MainMenuView(List<String> menuOptions) {
        System.out.println("Main menu options:");
        Display.displayList(menuOptions);
        Display.chooseOption();
    }
}
