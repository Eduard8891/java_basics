import java.util.*;

public class Main {

    public static void main(String[] args)
    {
        TreeMap <String , String> numBook = new TreeMap<>(); // Ключ, Значение

        numBook.put("89599564565", "Сергей");
        numBook.put("89564568573", "Елена");
        numBook.put("89564589665", "Брюс");
        numBook.put("89452564565", "Людовиг Х");

        Set<String> keys = numBook.keySet();

        Scanner scanner = new Scanner(System.in);

        for (;;)
        {
            String input = scanner.nextLine();

            if (input.equals("LIST"))
            {
                printMap (numBook);

                continue;
            }

            if ( numBook.containsValue(input)) // Совпадение значения
            {
                for(String key : keys)
                {
                    if( numBook.get(key).equals(input))
                    {
                        System.out.println(input + ", телефон: " + key);

                        break;
                    }
                }
            }

            if (numBook.containsKey(input)) // Совпадение ключа
            {
                    System.out.println(input+", имя: "+numBook.get(input));
            }


            else {
                if (input.matches("\\d{4,11}")) // Проверка на цифры
                {
                    System.out.println("Введите имя!");

                    String name = scanner.nextLine();

                    if (name.matches("[а-яёА-ЯЁ]+")) //Проверка на буквы
                    {
                        numBook.put((input), name);

                        System.out.println("Добавлено!");
                    }
                    else System.out.println("Неверный формат имени!");

                    continue;
                }
                if (input.matches("[а-яёА-ЯЁ]+")) //Проверка на буквы
                {
                    System.out.println("Введите номер!");

                    String number = scanner.nextLine();

                    if (number.matches("\\d{4,11}")) //Проверка на цифры
                    {
                        numBook.put((number), input);

                        System.out.println("Добавлено!");
                    }
                    else System.out.println("Неверный формат номера!");
                }
                else System.out.println("Неверный формат ввода!");
            }
        }
    }

    private static void printMap (Map <String, String> map)
    {
        for (String key: map.keySet())
        {
            System.out.println("Имя контакта: "+map.get(key) + ", телефон: " + key);
        }

    }
}
