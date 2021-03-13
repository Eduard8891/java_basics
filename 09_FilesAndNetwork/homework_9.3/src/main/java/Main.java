import java.io.IOException;
import java.text.ParseException;


public class Main {

    public static final String FILEPATH = "e:/movementList.CSV";

    public static void main(String[] args) throws IOException, ParseException {
        Movements mov = new Movements(FILEPATH);
        System.out.println("Сумма расходов: " + mov.getExpenseSum() + " руб.");
        System.out.println("Сумма доходов: " + mov.getIncomeSum() + " руб.");
        mov.details();


    }
}
