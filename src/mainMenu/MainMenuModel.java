package mainMenu;

import utils.User;

import java.util.List;

public class MainMenuModel {
    private User user;
    public final List<String> menuOptions = List.of("View Balance", "Deposit Money", "Withdraw Money",
            "Transfer Money", "Edit Profile", "Logout");

    public MainMenuModel(User user) {
        this.user = user;
    }

    public List<String> getMenuOptions() {
        return menuOptions;
    }

    public void handleOption(int selectedOption) throws IndexOutOfBoundsException {
        switch (selectedOption) {
            case 1 -> System.out.println("View balance");
            case 2 -> System.out.println("Deposit money");
            case 3 -> System.out.println("Withdraw money");
            case 4 -> System.out.println("Transfer money");
            case 5 -> System.out.println("Edit Profile");
            case 6 -> System.exit(1);
            default -> throw new IndexOutOfBoundsException();
        }
    }
}
