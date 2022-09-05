package homeMenu;

import utils.Display;

import java.util.List;

public class HomeMenuView {
    public HomeMenuView(List<String> menuOptions) {
        Display.clearScreen();
        System.out.println("Home menu options:");
        Display.displayList(menuOptions);
        Display.chooseOption();
    }
}
