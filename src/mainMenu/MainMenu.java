package mainMenu;

public class MainMenu {
    public MainMenu() {
        MainMenuModel model = new MainMenuModel();
        new MainMenuView(model.getMenuOptions());
        MainMenuController controller = new MainMenuController(model);
        controller.requestUserInput();
    }
}
