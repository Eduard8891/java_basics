package Account;

public class DepositaryAccount extends BankAccount
{

    public DepositaryAccount(int score)
    {
        super(score);
    }

    public String takeMoney (int input)
    {
        long now = System.currentTimeMillis();
        long sec = now-start;

        if (sec > 2592000000L)
        {
            score =  score - input;
        }
        else System.out.println("После пополнения счета еще не прошел месяц! До снятия осталось "+(2592000000L -sec)/3600000+" часов");

        return "Баланс счета: "+Integer.toString(score);
    }


}
