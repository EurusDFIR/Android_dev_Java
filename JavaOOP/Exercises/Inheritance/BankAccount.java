package Exercises.Inheritance;

public class BankAccount {
    private String accountNumber;
    private double balance;
    private String ownerName;

    public BankAccount(String accountNumber, double balance, String owerName) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.ownerName = owerName;

    }

    public void deposit(double money) {
        this.balance += money;
        System.out.println("Ban da nap " + money);
    }

    public void withdraw(double money) {
        if (this.balance >= 0 && money <= this.balance) {
            this.balance -= money;
            System.out.println("So du con lai la: " + this.balance);

        } else {
            System.out.println("So du khong du hoac thao tac khong hop le");

        }
    }

    public void checkBalance() {
        System.out.println("|--------------|");
        System.out.println("Thong tin tai khoan: cua khach hang: " + this.ownerName);
        System.out.println("So tai khoan: " + accountNumber);
        System.out.println("So du hien tai la: " + this.balance);
        System.out.println("|--------------|");

    }

    public static void main(String[] args) {
        BankAccount bankaccount = new BankAccount("id11222", 50000, "Eury");
        bankaccount.checkBalance();

    }

}
