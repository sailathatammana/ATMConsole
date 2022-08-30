package homeMenu;

import Register.Register;

import java.util.List;

public class HomeMenuModel {
    Register register = new Register();
    public final List<String> menuOptions = List.of("Login", "Register");

    public List<String> getMenuOptions() {
        return menuOptions;
    }

    public void handleOption(int selectedOption) throws IndexOutOfBoundsException {
        switch (selectedOption) {
            case 1 -> System.out.println("Login");
            case 2 -> register.adduser();
            default -> throw new IndexOutOfBoundsException();
        }
    }
}
