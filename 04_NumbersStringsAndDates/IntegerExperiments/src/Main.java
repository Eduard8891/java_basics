import java.util.Scanner;

public class Main
{
    public static void main(String[] args){

        sumDigits(12345);
        sumDigits(10);
        sumDigits(5059191);

        Container container = new Container();
        container.count += 7843;

    }

    public static Integer sumDigits(Integer number){
        String sLength1 = number.toString();
        int iLength = sLength1.length();
        int sum = 0;
        for (int num = 0; num < iLength; num++) {
            char p = sLength1.charAt(num);
            int r = Character.getNumericValue(p);
            sum = sum + r;
        }
        System.out.println("Сумма чисел числа "+number+" равна "+sum);
        return 0;
    }
}
