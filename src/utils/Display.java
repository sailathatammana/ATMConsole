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
}
