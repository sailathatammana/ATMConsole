package mainMenu;

import utils.Display;
import utils.FileHandler;
import register.User;

import java.util.List;
import java.util.Scanner;

public class MainMenuModel {
    private final List<User> users;
    private final int index;
    FileHandler fileHandler = new FileHandler();
    private Scanner scanner;

    public MainMenuModel(List<User> users, int index) {
        this.users = users;
        this.index = index;
        scanner = new Scanner(System.in);
    }

    public final List<String> menuOptions = List.of("View Balance", "Deposit Money", "Withdraw Money",
            "Transfer Money", "Edit Profile", "Logout");

    public List<String> getMenuOptions() {
        return menuOptions;
    }

    public void handleOption(int selectedOption) throws IndexOutOfBoundsException {
        switch (selectedOption) {
            case 1 -> viewBalance();
            case 2 -> addMoney();
            case 3 -> withdrawMoney();
            case 4 -> System.out.println("Transfer money");
            case 5 -> System.out.println("Edit Profile");
            case 6 -> exit();
            default -> throw new IndexOutOfBoundsException();
        }
    }

    private void viewBalance() {
        System.out.println("Balance is: " + users.get(index).getBalance() + "SEK");
    }

    private void addMoney() {
        double balance = users.get(index).getBalance();
        while (true) {
            try {
                System.out.print("Enter amount to add: ");
                String input = scanner.nextLine();
                double selectedInput = Double.parseDouble(input);
                if (selectedInput > 0) {
                    balance = balance + selectedInput;
                    users.get(index).setBalance(balance);
                    System.out.println("Your total balance is: " +balance+"SEK");
                    break;
                }
                System.out.println("Enter the value of greater than zero");
            } catch (NumberFormatException e) {
                Display.printInvalidOption();
            }
        }
        fileHandler.writeToFile(users);
    }

    private void withdrawMoney() {
        double balance = users.get(index).getBalance();
        while (true) {
            try {
                System.out.print("Enter amount to withdraw: ");
                String input = scanner.nextLine();
                double selectedInput = Double.parseDouble(input);
                if (selectedInput > 0 && selectedInput < balance) {
                    balance = balance - selectedInput;
                    users.get(index).setBalance(balance);
                    System.out.println("Your remaining balance is: " +balance+"SEK");
                    break;
                } else if (selectedInput <= 0) {
                    System.out.println("Enter a value greater than zero");
                } else {
                    System.out.println("Insufficient balance to withdraw");
                }
            } catch (NumberFormatException e) {
                Display.printInvalidOption();
            }
        }
        fileHandler.writeToFile(users);
    }

    private void exit() {
        System.exit(1);
    }
}
