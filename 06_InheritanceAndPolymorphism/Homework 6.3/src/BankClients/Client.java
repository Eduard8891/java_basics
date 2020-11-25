package BankClients;

public abstract class Client  {

    protected String account;
    protected int balance;

    public Client (String account, int balance)
    {
        this.account = account;
        this.balance = balance;
    }

    public String getBalance (String account) {
        return "Баланс: " + balance;
    }

    public void setBalance (String account, int balance) {
        this.balance = balance;
    }

    public String addMoney (String account, int money)
    {
        balance += money;

        return "Сумма пополнения расчетного счета №" + account + ": " + money;
    }

    public String takeMoney (String account, int money)
    {
        balance -= money;

        return "Снимаемая сумма с расчетного счета №" + account + ": " + money;
    }

}
