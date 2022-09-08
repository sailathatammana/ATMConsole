package mainMenu;

import menuActions.BalanceInquiry;
import menuActions.EditProfile;
import menuActions.Transaction;
import utils.Display;
import utils.User;

import java.util.List;

public class MainMenuModel {
    BalanceInquiry balance;
    Transaction transaction;
    EditProfile editProfile;

    public MainMenuModel(List<User> users, int index) {
        balance = new BalanceInquiry(users, index);
        transaction = new Transaction(users, index);
        editProfile = new EditProfile(users, index);
    }

    public final List<String> menuOptions = List.of("View Balance", "Deposit Money", "Withdraw Money",
            "Transfer Money", "Edit Profile", "Logout");

    public List<String> getMenuOptions() {
        return menuOptions;
    }

    public void handleOption(int selectedOption) throws IndexOutOfBoundsException {
        switch (selectedOption) {
            case 1 -> balance.viewBalance();
            case 2 -> transaction.addMoney();
            case 3 -> transaction.withdrawMoney();
            case 4 -> transaction.transferMoney();
            case 5 -> editProfile.updateProfile();
            case 6 -> Display.exit();
            default -> throw new IndexOutOfBoundsException();
        }
    }
}
