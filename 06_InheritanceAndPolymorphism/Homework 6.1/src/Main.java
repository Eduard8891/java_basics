import Account.CardAccount;
import Account.DepositaryAccount;
import Account.BankAccount;

public class Main {

    public static void main(String[] args)
    {
        BankAccount bankAccount = new BankAccount(10000);
        System.out.println(bankAccount.putMoney(500));
        System.out.println(bankAccount.takeMoney(1000));

        System.out.println("--------------------------------");

        DepositaryAccount depositaryAccount = new DepositaryAccount(5000);
        System.out.println(depositaryAccount.takeMoney(500));

        System.out.println("--------------------------------");

        CardAccount cardAccount = new CardAccount(8000);
        System.out.println(cardAccount.putMoney(500));
        System.out.println(cardAccount.takeMoney(500));

        System.out.println("--------------------------------");

        System.out.println(cardAccount.send(depositaryAccount,5000));
        System.out.println(depositaryAccount.balanceAccount());
        System.out.println(cardAccount.balanceAccount());
        System.out.println(bankAccount.balanceAccount());



    }
}
