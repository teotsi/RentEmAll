package classes;

public class BankAccount {
    private String accountHolder;
    private String iban;

    public BankAccount(String accountHolder, String iban) {
        this.accountHolder = accountHolder;
        this.iban = iban;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public String getIban() {
        return iban;
    }
}
