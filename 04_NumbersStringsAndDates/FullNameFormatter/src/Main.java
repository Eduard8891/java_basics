import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ФИО!");
        while (true) {
            String iinput = scanner.nextLine();
            String input = iinput.trim();
            if (input.equals("0")) {
                System.out.println("Введенная строка не является ФИО");
                break;
            }
            else if (input.length() < 5) {
                System.out.println("Введенная строка не является ФИО");
                break;
            }

            else if (input.indexOf(' ') < 2) {
                System.out.println("Введенная строка не является ФИО");
                break;
            }

            else if (input.length() > 5) {
                String family = input.substring(0, input.indexOf(' '));
                System.out.println("Фамилия: " + family.trim());

                String name = input.substring(input.indexOf(' '), input.lastIndexOf(' '));
                System.out.println("Имя: " + name.trim());

                int lNum = input.length();
                String mName = input.substring(input.lastIndexOf(' '), lNum);
                System.out.println("Отчество: " + mName.trim());
                break;
            }


        }
    }

}