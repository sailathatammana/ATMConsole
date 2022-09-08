package utils;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ValidateHelper {
    public boolean validateUserAmount(double selectedInput, double balance, String value) {
        if (selectedInput > 0 && selectedInput <= balance) {
            return true;
        } else if (selectedInput <= 0) {
            System.out.println("Enter a value greater than zero");
        } else {
            System.out.println("Insufficient balance to " + value);
        }
        return false;
    }

    public boolean validateFullName(String input, List<User> users) {
        for (User userData : users) {
            if ((userData.getFullName().equals(input))) {
                System.out.println("Full name already exists.");
                return false;
            }
        }
        return true;
    }

    public boolean validateUserName(String input, List<User> users) {
        for (User userData : users) {
            if ((userData.getUserName().equals(input))) {
                System.out.println("User name already exists.");
                return false;
            }
        }
        return true;
    }

    public String readInputFromUser(String message, String errorMessage, String condition) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(message);
        String input = scanner.nextLine();
        while (isCheck(condition, input)) {
            System.out.print(errorMessage);
            input = scanner.nextLine();
        }
        return input;
    }

    private boolean isCheck(String condition, String input) {
        boolean check;
        if (Objects.equals(condition, "register")) {
            check = (input.equals("") || (input.startsWith(" ")) || (input.endsWith(" ")));
        } else {
            check = (input.startsWith(" ")) || (input.endsWith(" "));
        }
        return check;
    }
}
