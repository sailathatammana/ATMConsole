package menuActions;

import register.Authentication;
import register.User;
import utils.Display;
import utils.FileHandler;

import java.util.List;
import java.util.Scanner;

public class Transfer {
    private final List<User> users;
    private final int index;
    private int userId;
    Authentication auth;
    FileHandler fileHandler;
    private final Scanner scanner;

    public Transfer(List<User> users, int index) {
        this.users = users;
        this.index = index;
        scanner = new Scanner(System.in);
        auth = new Authentication();
        fileHandler = new FileHandler();
    }

    public void sendMoney() {
        boolean done;
        double balance = users.get(index).getBalance();
        String userName;
        while (true) {
            userName = readInputFromUser("Enter user Name: ");
            done = validateLoginDetails(userName);
            if (done) {
                double balance1 = users.get(userId).getBalance();
                while (true) {
                    try {
                        System.out.print("Enter amount to transfer: ");
                        String input = scanner.nextLine();
                        double selectedInput = Double.parseDouble(input);
                        if (selectedInput > 0 && selectedInput < balance) {
                            balance1 = balance1 + selectedInput;
                            balance = balance - selectedInput;
                            users.get(userId).setBalance(balance1);
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
                break;
            } else {
                System.out.println("User not found");
            }
        }
        fileHandler.writeToFile(users);
        Display.returnMainMenu();
    }

    private String readInputFromUser(String message) {
        System.out.print(message);
        String input = scanner.nextLine();
        return input;
    }

    private boolean validateLoginDetails(String userName) {
        for (User userData : users) {
            if ((userData.getUserName().equals(userName))) {
                userId = users.indexOf(userData);
                return true;
            }
        }
        return false;
    }
}
