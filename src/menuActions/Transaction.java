package menuActions;

import utils.Display;
import utils.FileHandler;
import utils.User;
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
            if (validateBalance("withdraw")) break;
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
            if (validateUsers()) break;
            if (validateBalance("transfer")) break;
            String fullName = Display.readInputFromUser("Enter user full name: ");
            done = validateUser(fullName);
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

    private boolean validateUsers() {
        if (users.size() < 2) {
            System.out.println("No register user available to transfer money");
            return true;
        }
        return false;
    }

    private boolean validateBalance(String value) {
        if (senderBalance == 0) {
            System.out.println("Your balance is 0.Please add some money to " + value);
            return true;
        }
        return false;
    }

    private boolean validateUser(String fullName) {
        for (User userData : users) {
            if ((userData.getFullName().equals(fullName))) {
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
