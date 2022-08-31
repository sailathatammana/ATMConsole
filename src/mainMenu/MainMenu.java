package mainMenu;

import utils.User;

public class MainMenu {
    public MainMenu(User user) {
        MainMenuModel model = new MainMenuModel(user);
        new MainMenuView(model.getMenuOptions(),user);
        MainMenuController controller = new MainMenuController(model);
        controller.requestUserInput();
    }
}
