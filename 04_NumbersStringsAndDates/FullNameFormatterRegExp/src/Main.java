import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    final static String REG_LAST_NAME = "([А-ЯЁ][а-яё]+[\\s|-]?([А-ЯЁ]?[а-яё]+))";
    final static String REG_NAME = "([А-ЯЁ][а-яё]*-?([А-ЯЁ]?[а-яё]+))";
    final static String REG_FULL_NAME = String.format("^%s %s %s$", REG_NAME, REG_NAME, REG_LAST_NAME);

    public static void main(String[] args) {

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите ФИО!");
            String input = scanner.nextLine();

            Pattern name = Pattern.compile(REG_NAME);
            Matcher inN = name.matcher(input);
            Pattern lname = Pattern.compile(REG_LAST_NAME);
            Matcher inLN = lname.matcher(input);

            if (input.matches(REG_FULL_NAME)) {
                output(input);
                break;
            }
            else System.out.println("Неправильный формат ввода! Поробуйте еще раз.");

        }
    }
    public static String output (String input) {
        String [] fio = input.split("\\s+");
        System.out.println("Фамилия: " + fio[0]);
        System.out.println("Имя: " + fio[1]);
        System.out.print("Отчество: " + fio[2]);
        if (fio.length == 4) {
            System.out.println(" " + fio[3]);
            }
        return input;
    }
}