package BankClients;

public class SelfEmployed extends Client {
    public SelfEmployed(String account, int balance) {
        super(account, balance);
    }


    public String addMoney (String account, int money)
    {
        if (money < 1000)
        {
            balance += money - money/100;
            return "Сумма пополнения расчетного счета №" + account + ": " + money + ", комиссия: " + money/100;
        }
        if (money >= 1000)
        {
            balance += money - money/200;
            return "Сумма пополнения расчетного счета №" + account + ": " + money + ", комиссия: " + money/200;
        }
        return "Error";
    }

    public String takeMoney (String account, int money)
    {
        balance -= money;

        return "Снимаемая сумма с расчетного счета №" + account + ": " + money;
    }
}
