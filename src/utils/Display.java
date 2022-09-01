package utils;

import java.util.List;

public class Display {
    public static void chooseOption(){
        System.out.print("Please choose an option: ");
    }

    public static void displayList(List<String> options){
        for (int i = 1; i <= options.size(); i++) {
            System.out.println("[" + i + "]" + " " + options.get(i - 1));
        }
    }

    public static void printInvalidOption() {
        System.out.println("⚠️ Invalid option");
    }

    public static String errorMessage(String value) {
        String message = "Invalid Entry, " + value + " should not be empty or starts with space\nPlease enter valid " + value + ": ";
        return message;
    }
}
