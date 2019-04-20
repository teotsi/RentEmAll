package classes;

public class BankAccount {
    private String accountHolder;
    private String iban;
    private double balance;

    public BankAccount(String accountHolder, String iban, double balance) {
        this.accountHolder = accountHolder;
        this.iban = iban;
        this.balance = balance;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public String getIban() {
        return iban;
    }

    public void addBalance(double moneyyy) {
        balance += moneyyy;
    }

    @Override
    public String toString() {
        return getAccountHolder() + "/" + getIban() + "/" + balance;
    }
}
