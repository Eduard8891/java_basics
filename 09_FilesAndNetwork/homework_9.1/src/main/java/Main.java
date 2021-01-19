import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите путь к папке! (например: \"c:/games/etc\")");

        while (true) {

            String input = scanner.nextLine();

            if (input.matches("^[A-Za-z]:/(\\w+(/)*)+")) {

                FileUtils.calculateFolderSize(input);

            } else System.out.println("Неверный путь! Попробуйте еще раз.");
        }
    }
}
