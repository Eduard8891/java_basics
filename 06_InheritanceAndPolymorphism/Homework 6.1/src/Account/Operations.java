package Account;

public class Operations {

    public int score;
    static long start;

    public Operations(int score)
    {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String putMoney (int input)
    {
        start = System.currentTimeMillis();
        score = score + input;
        return "Сумма пополнения "+input;
    }

    public String takeMoney (int input)
    {
        score = score - input;
        return "Снимаемая сумма "+input;
    }

    public String balanceAccount ()
    {
        return "Денег на счете "+score;
    }
}
