package BankClients;

public class Individual extends Client {

    public Individual(String account, int balance) {
        super(account, balance);
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
