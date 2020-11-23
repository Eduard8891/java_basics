package Account;

public class CardAccount extends Operations
{
    public CardAccount(int score) {
        super(score);
    }

    public String takeMoney (int input)
    {

        score = score - input;

        return "Сумма "+(input-input/100)+" комиссия "+input/100;
    }
}
