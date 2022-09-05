package utils;

public class ValidateHelper {
    public boolean validateUserAmount(double selectedInput, double balance, String value) {
        if (selectedInput > 0 && selectedInput < balance) {
            return true;
        } else if (selectedInput <= 0) {
            System.out.println("Enter a value greater than zero");
        } else {
            System.out.println("Insufficient balance to " + value);
        }
        return false;
    }
}
