import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;



public class Main {

    public static void main(String[] args) throws Exception {

        int day = 17;
        int month = 3;
        int year = 1988;
 
        collectBirthdays(year, month, day);

    }

    public static String collectBirthdays(int year, int month, int day) throws Exception {


        DateFormat dateformat  = new SimpleDateFormat("dd.MM.yyyy EEEE");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, day);
        int j = cal.get(Calendar.YEAR) - year;
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        for (int i=0; i <= j; i++ ){
            cal.set(Calendar.YEAR, year+i);
            System.out.println(i+" - "+dateformat.format(cal.getTime()));
        }
        return "";
    }
}
