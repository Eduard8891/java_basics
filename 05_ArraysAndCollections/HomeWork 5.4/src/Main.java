import java.util.*;

public class Main {

    static HashMap <String , String> numBook = new HashMap<>(); // Ключ, Значение
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)
    {
        numBook.put("89599564565", "Сергей");
        numBook.put("89564568573", "Елена");
        numBook.put("89564589665", "Брюс");
        numBook.put("89452564565", "Людовиг Х");

        Set<String> keys = numBook.keySet();

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
            else
            if (input.matches("\\d{4,11}")) // Проверка на цифры
            {
                addNumber (input);
            }
            if (input.matches("[а-яёА-ЯЁ]+"))
            {
                addName (input);
            }
        }
    }


    private static void addNumber (String input)
        {
            System.out.println("Введите имя!");

            String name = scanner.nextLine();

            if (name.matches("[а-яёА-ЯЁ]+")) //Проверка на буквы
            {
                putNumber(input, name);
            }
            else System.out.println("Неверный формат имени!");
    }


    private static void addName (String input) {

        System.out.println("Введите номер!");

        String number = scanner.nextLine();

        if (number.matches("\\d{4,11}")) //Проверка на цифры
        {
            putNumber(number, input);

        } else System.out.println("Неверный формат номера!");
    }


    private static void putNumber (String number, String name)
    {
        numBook.put(number, name);

        System.out.println("Добавлено!");
    }


    private static void printMap (Map <String, String> map)
    {
        for (String key: map.keySet())
        {
            System.out.println("Имя контакта: "+map.get(key) + ", телефон: " + key);
        }

    }
}
