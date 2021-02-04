import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите путь к папке! (например: \"c:/games/etc\")");

        while (true) {

            String input = scanner.nextLine();

            if (input.matches("^[A-Za-z]:/(\\w+(/)*)+")) {

//                Path inputPath = Paths.get(input);
                FileUtils.calculateFolderSize(input);

            } else System.out.println("Неверный путь! Попробуйте еще раз.");
        }
    }
}
