package mainMenu;

import menuActions.EditProfile;
import menuActions.Transfer;
import utils.Display;
import utils.FileHandler;
import register.User;

import java.util.List;
import java.util.Scanner;

public class MainMenuModel {
    private final List<User> users;
    private final int index;
    FileHandler fileHandler = new FileHandler();
    EditProfile editProfile;
    Transfer transfer;
    private final Scanner scanner;

    public MainMenuModel(List<User> users, int index) {
        this.users = users;
        this.index = index;
        scanner = new Scanner(System.in);
        editProfile = new EditProfile(users, index);
        transfer = new Transfer(users, index);
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
            case 4 -> transfer.sendMoney();
            case 5 -> editProfile.updateProfile();
            case 6 -> Display.exit();
            default -> throw new IndexOutOfBoundsException();
        }
    }

    private void viewBalance() {
        Display.clearScreen();
        System.out.println("Balance is: " + users.get(index).getBalance() + "SEK");
        Display.returnMainMenu();
    }

    private void addMoney() {
        Display.clearScreen();
        double balance = users.get(index).getBalance();
        while (true) {
            try {
                System.out.print("Enter amount to add: ");
                String input = scanner.nextLine();
                double selectedInput = Double.parseDouble(input);
                if (selectedInput > 0) {
                    balance = balance + selectedInput;
                    users.get(index).setBalance(balance);
                    System.out.println("Your total balance is: " + balance + "SEK");
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

    private void withdrawMoney() {
        Display.clearScreen();
        double balance = users.get(index).getBalance();
        while (true) {
            try {
                System.out.print("Enter amount to withdraw: ");
                String input = scanner.nextLine();
                double selectedInput = Double.parseDouble(input);
                if (selectedInput > 0 && selectedInput < balance) {
                    balance = balance - selectedInput;
                    users.get(index).setBalance(balance);
                    System.out.println("Your remaining balance is: " + balance + "SEK");
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
        Display.returnMainMenu();
    }
}
