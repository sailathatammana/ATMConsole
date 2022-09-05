package menuActions;

import register.Authentication;
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
    private double balance;
    Authentication auth;
    FileHandler fileHandler;
    ValidateHelper helper;
    private final Scanner scanner;

    public Transaction(List<User> users, int index) {
        this.users = users;
        this.index = index;
        this.balance = users.get(index).getBalance();
        auth = new Authentication();
        fileHandler = new FileHandler();
        helper = new ValidateHelper();
        scanner = new Scanner(System.in);
    }

    public void addMoney() {
        Display.clearScreen();
        while (true) {
            try {
                System.out.print("Enter amount to add: ");
                String input = scanner.nextLine();
                double selectedInput = Double.parseDouble(input);
                if (selectedInput > 0) {
                    balance = balance + selectedInput;
                    users.get(index).setBalance(balance);
                    System.out.println("Your updated total balance is: " + balance + "SEK");
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
                double selectedInput = Double.parseDouble(input);
                if (helper.validateUserAmount(selectedInput, balance, "withdraw")) {
                    balance = balance - selectedInput;
                    users.get(index).setBalance(balance);
                    System.out.println("Your remaining the balance is: " + balance + "SEK");
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
        double balance1 = users.get(userId).getBalance();
        while (true) {
            try {
                System.out.print("Enter amount to transfer: ");
                String input = scanner.nextLine();
                double selectedInput = Double.parseDouble(input);
                if (helper.validateUserAmount(selectedInput, balance, "transfer")) {
                    balance1 = balance1 + selectedInput;
                    balance = balance - selectedInput;
                    users.get(userId).setBalance(balance1);
                    users.get(index).setBalance(balance);
                    System.out.println("Your remaining balance is: " + balance + "SEK");
                    break;
                }
            } catch (NumberFormatException e) {
                Display.printInvalidOption();
            }
        }
    }
}
