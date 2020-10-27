import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;



public class Main {

    public static void main(String[] args) throws Exception {

        int day = 17;
        int month = 4;
        int year = 1988;
 
        collectBirthdays(year, month, day);

    }

    public static String collectBirthdays(int year, int month, int day) throws Exception {


        DateFormat dateformat  = new SimpleDateFormat("dd.MM.yyyy");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, day);
        String [] dayOfWeek = {"000", "Воскресенье", "Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота",};
        int j = cal.get(Calendar.YEAR) - year;
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        for (int i=0; i <= j; i++ ){
            cal.set(Calendar.YEAR, year+i);
            int k = cal.get(Calendar.DAY_OF_WEEK);
            System.out.println(i+" - "+dateformat.format(cal.getTime())+" "+dayOfWeek[k]);
        }
        return "";
    }
}
