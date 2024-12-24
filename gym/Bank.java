package gym;

public class Bank {
    private int balance;
    public Bank(int balance) {
        this.balance = balance;
    }
    public int getBalance() {
        return balance;
    }
    public void pay(int amount) {
        balance -= amount;
    }
    public void addMoneyToMyAcc(int amount) {
        balance += amount;
    }
}
