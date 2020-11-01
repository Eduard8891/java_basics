import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main {

    public static void main(String[] args){

        int day = 17;
        int month = 4;
        int year = 1988;

        collectBirthdays(year, month, day);

    }

    public static String collectBirthdays(int year, int month, int day){


        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        Calendar birthday = Calendar.getInstance();

        Calendar now = Calendar.getInstance();

        birthday.set(Calendar.DAY_OF_MONTH, day);

        birthday.set(Calendar.YEAR, year);

        birthday.set(Calendar.MONTH, month-1);

        int i = 0;

        String [] dayOfWeek = {"000", "Воскресенье", "Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота",};

        while(birthday.before(now)) {

            birthday.set(Calendar.YEAR, year);

            int k = birthday.get(Calendar.DAY_OF_WEEK);

            System.out.println(i+" "+format.format(birthday.getTime())+" "+dayOfWeek[k]);

            i++;

            year++;
        }
        return "";
    }
}