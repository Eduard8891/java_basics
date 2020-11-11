import java.util.Scanner;
import java.util.TreeSet;

public class Main {


    public static void main(String[] args) {

        TreeSet<String> emails = new TreeSet<>();

        emails.add("ololohov@gmail.com");
        emails.add("monday@mail.ru");
        emails.add("doremi8879@xaker.ru");

        while (true) {

            Scanner scanner = new Scanner(System.in);
            String operationEmails = scanner.nextLine();
            String[] splitIn = operationEmails.split(" ", 2);

            switch (splitIn[0]) {

                case "ADD":

                    if (splitIn.length < 2)
                    {
                        System.out.println("Вы не ввели почтовый ящик!");

                        break;
                    }

                    if (splitIn.length > 2 )
                    {
                        System.out.println("Некорректный формат ввода!");

                        break;
                    }

                    if (splitIn[1].matches("^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")) {

                        emails.add(splitIn[1]);

                        System.out.println("Почтовый ящик добавлен!");
                    }
                    else {

                        System.out.println("Некорректный почтовый ящик!");
                    }
                    break;



                case "LIST":

                    for (String email: emails) {

                        System.out.println(email);

                    }
                    break;


                default:

                System.out.println("Неверная команда!");
            }

        }
    }
}
