package homeMenu;

import register.Register;
import register.Login;

import java.util.List;

public class HomeMenuModel {
    Register register = new Register();
    Login login = new Login();
    public final List<String> menuOptions = List.of("Login", "register");

    public List<String> getMenuOptions() {
        return menuOptions;
    }

    public void handleOption(int selectedOption) throws IndexOutOfBoundsException {
        switch (selectedOption) {
            case 1 -> login.LoginUser();
            case 2 -> register.adduser();
            default -> throw new IndexOutOfBoundsException();
        }
    }
}
