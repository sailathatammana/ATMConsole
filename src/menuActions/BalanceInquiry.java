package menuActions;

import register.User;
import utils.Display;

import java.util.List;

public class BalanceInquiry {
    private final List<User> users;
    private final int index;
    public BalanceInquiry(List<User> users, int index){
        this.users = users;
        this.index = index;
    }
    public void viewBalance() {
        Display.clearScreen();
        System.out.println("Balance is: " + users.get(index).getBalance() + "SEK");
        Display.returnMainMenu();
    }
}
