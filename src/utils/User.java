package utils;

public class User {
    private String fullName;
    private String uniqueName;
    private String password;
    private double balance;

    public User(String fullName, String uniqueName, String password) {
        this.fullName = fullName;
        this.uniqueName = uniqueName;
        this.password = password;
    }

    public User(String uniqueName, String password) {
        this.uniqueName = uniqueName;
        this.password = password;
    }

    public User(double balance) {
        this.balance = balance;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUniqueName() {
        return uniqueName;
    }

    public void setUniqueName(String uniqueName) {
        this.uniqueName = uniqueName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
