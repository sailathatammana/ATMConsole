package mainMenu;

import register.User;

import java.util.List;

public class MainMenu {
    public MainMenu(List<User> users, int index) {
        MainMenuModel model = new MainMenuModel(users, index);
        new MainMenuView(model.getMenuOptions(), users, index);
        MainMenuController controller = new MainMenuController(model);
        controller.requestUserInput();
    }
}
