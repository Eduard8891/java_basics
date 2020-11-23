import Account.CardAccount;
import Account.DepositaryAccount;
import Account.Operations;

public class Main {

    public static void main(String[] args)
    {
        Operations operations = new Operations(10000);
        System.out.println(operations.putMoney(500));
        System.out.println(operations.takeMoney(1000));
        System.out.println(operations.balanceAccount());
        System.out.println("--------------------------------");

        DepositaryAccount depositaryAccount = new DepositaryAccount(5000);
        System.out.println(depositaryAccount.takeMoney(500));
        System.out.println("--------------------------------");

        CardAccount cardAccount = new CardAccount(8000);
        System.out.println(cardAccount.putMoney(500));
        System.out.println(cardAccount.takeMoney(500));


    }
}
