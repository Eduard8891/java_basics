import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите путь к папке!");

        String firstPath = scanner.nextLine();

        if (firstPath.matches("^[A-Za-z]:/(.+/*)*")) {
            System.out.println("Введите путь для копирования папки!");
            String secondPath = scanner.nextLine();


            if (secondPath.matches("^[A-Za-z]:/(.+/*)*")) {
                FileUtils.copyFolder(firstPath, secondPath);

            } else System.out.println("Неверный путь! Попробуйте еще раз.");

        } else System.out.println("Неверный путь! Попробуйте еще раз.");
    }
}
