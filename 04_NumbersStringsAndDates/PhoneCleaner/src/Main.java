import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите номер телефона!");
            String input = scanner.nextLine();

            if (input.equals("0")) {
                break;
            }
            String enumber = input.replaceAll("[^0-9]","");
            String number = enumber.trim();

            if (number.length() == 11) {
                char x = number.charAt(0);
                int y = x - '0';

                if (y == 8) {
                    System.out.println("7"+number.substring(1,11));
                    break;
                }
                if (y == 7) {
                    System.out.println(number);
                    break;
                }
                else {
                    System.out.println("Формат номера "+number+" неверный!");
                }
            }

            if (number.length() == 10){
                System.out.println("7"+number);
                break;
            }
            else  {
                System.out.println("Формат номера "+number+" неверный!");
            }
            }
        }
    }


