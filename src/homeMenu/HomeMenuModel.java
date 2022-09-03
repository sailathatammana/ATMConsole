package homeMenu;

import register.Register;
import register.Login;
import utils.Display;

import java.util.List;

public class HomeMenuModel {
    Register register = new Register();
    Login login = new Login();
    public final List<String> menuOptions = List.of("Login", "register", "Exit");

    public List<String> getMenuOptions() {
        return menuOptions;
    }

    public void handleOption(int selectedOption) throws IndexOutOfBoundsException {
        switch (selectedOption) {
            case 1 -> login.LoginUser();
            case 2 -> register.adduser();
            case 3 -> Display.exit();
            default -> throw new IndexOutOfBoundsException();
        }
    }
}
