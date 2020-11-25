package BankClients;

public abstract class Client  {

    protected String account;
    protected int balance;

    public Client (String account, int balance)
    {
        this.account = account;
        this.balance = balance;
    }

    public String getBalance (String account)
    {
        return "Баланс: " + balance;
    }

    public abstract String addMoney (String account, int money);

    public abstract String takeMoney (String account, int money);


}
