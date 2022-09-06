package menuActions;

import register.User;
import utils.Display;
import utils.FileHandler;
import utils.ValidateHelper;

import java.util.List;
import java.util.Scanner;

public class Transaction {
    private final List<User> users;
    private final int index;
    private int userId;
    private float senderBalance;
    FileHandler fileHandler;
    ValidateHelper validateHelper;
    private final Scanner scanner;

    public Transaction(List<User> users, int index) {
        this.users = users;
        this.index = index;
        this.senderBalance = users.get(index).getBalance();
        fileHandler = new FileHandler();
        validateHelper = new ValidateHelper();
        scanner = new Scanner(System.in);
    }

    public void addMoney() {
        Display.clearScreen();
        while (true) {
            try {
                System.out.print("Enter amount to add: ");
                String input = scanner.nextLine();
                float selectedInput = Float.parseFloat(input);
                if (selectedInput > 0) {
                    senderBalance = senderBalance + selectedInput;
                    users.get(index).setBalance(senderBalance);
                    System.out.println("Your updated total balance is: " + senderBalance + "SEK");
                    break;
                }
                System.out.println("Enter the value of greater than zero");
            } catch (NumberFormatException e) {
                Display.printInvalidOption();
            }
        }
        fileHandler.writeToFile(users);
        Display.returnMainMenu();
    }

    public void withdrawMoney() {
        Display.clearScreen();
        while (true) {
            try {
                System.out.print("Enter amount to withdraw: ");
                String input = scanner.nextLine();
                float selectedInput = Float.parseFloat(input);
                if (validateHelper.validateUserAmount(selectedInput, senderBalance, "withdraw")) {
                    senderBalance = senderBalance - selectedInput;
                    users.get(index).setBalance(senderBalance);
                    System.out.println("Your remaining the balance is: " + senderBalance + "SEK");
                    break;
                }
            } catch (NumberFormatException e) {
                Display.printInvalidOption();
            }
        }
        fileHandler.writeToFile(users);
        Display.returnMainMenu();
    }

    public void transferMoney() {
        Display.clearScreen();
        boolean done;
        while (true) {
            String userName = Display.readInputFromUser("Enter user name: ");
            done = validateUser(userName);
            if (done) {
                readAmount();
                break;
            } else {
                System.out.println("User not found");
            }
        }
        fileHandler.writeToFile(users);
        Display.returnMainMenu();
    }

    private boolean validateUser(String userName) {
        for (User userData : users) {
            if ((userData.getUserName().equals(userName))) {
                userId = users.indexOf(userData);
                return true;
            }
        }
        return false;
    }

    private void readAmount() {
        float receiverBalance = users.get(userId).getBalance();
        while (true) {
            try {
                System.out.print("Enter amount to transfer: ");
                String input = scanner.nextLine();
                float selectedInput = Float.parseFloat(input);
                if (validateHelper.validateUserAmount(selectedInput, senderBalance, "transfer")) {
                    receiverBalance = receiverBalance + selectedInput;
                    senderBalance = senderBalance - selectedInput;
                    users.get(userId).setBalance(receiverBalance);
                    users.get(index).setBalance(senderBalance);
                    System.out.println("Your remaining balance is: " + senderBalance + "SEK");
                    break;
                }
            } catch (NumberFormatException e) {
                Display.printInvalidOption();
            }
        }
    }
}
