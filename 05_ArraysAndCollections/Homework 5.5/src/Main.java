import java.util.*;

public class Main
{
    static long start;
    static ArrayList <String> carNumbers = new ArrayList<>();
    static  HashSet <String> hashsetCarNumbers = new HashSet<>();
    static  TreeSet <String> treeSetCarNumbers = new TreeSet<>();

    public static void main(String[] args)
    {
        String [] letters = {"А", "В", "Е", "К", "М", "Н", "О", "Р", "С", "Т", "У", "Х"};

        for (int letterF = 0; letterF <= (letters.length-1); letterF++)
        {
            String letter = letters[letterF];

            for (int numF = 0; numF <= 9; numF++)
            {
                        for (int numL = 1; numL <= 199; numL++)
                        {
                            String carNumber = letter+String.format("%03d", numF)+letter+letter + String.format("%02d", numL);

                            System.out.println(carNumber);

                            carNumbers.add(carNumber);

                            hashsetCarNumbers.add(carNumber);

                            treeSetCarNumbers.add(carNumber);

                            Collections.sort(carNumbers);
                        }
            }
        }
        Scanner scanner = new Scanner(System.in);

        while (true)
        {

            String input = scanner.nextLine();

            searchArraylist(input);

            binarySearchArraylist(input);

            searchHashSet(input);

            searchTreeSet(input);
        }
    }

    private static void searchHashSet (String input)
    {
        start = System.currentTimeMillis();

        if (hashsetCarNumbers.contains(input))
        {
            long duration = System.currentTimeMillis() - start;

            System.out.println("Номер "+input+" найден в HashSet за "+duration+" мс");
        }
        else System.out.println("Номер "+input+" не найден");
    }


    private static void searchTreeSet (String input)
    {
        start = System.currentTimeMillis();

        if (treeSetCarNumbers.contains(input))
        {
            long duration = System.currentTimeMillis() - start;

            System.out.println("Номер "+input+" найден в TreeSet за "+duration+" мс");
        }
        else System.out.println("Номер "+input+" не найден");
    }

    private static void searchArraylist (String input)
    {
        start = System.currentTimeMillis();

        if (carNumbers.contains(input))
        {
            long duration = System.currentTimeMillis() - start;

            System.out.println("Номер "+input+" найден прямым перебором за "+duration+" мс");
        }
        else System.out.println("Номер "+input+" не найден");
    }

    private static void binarySearchArraylist (String input)
    {
        start = System.currentTimeMillis();

        int binarySearch = Collections.binarySearch(carNumbers, input);

        if (binarySearch != -1)
        {
            long duration = System.currentTimeMillis() - start;

            System.out.println("Номер "+input+" найден бинарным поиском за "+duration+" мс");
        }
        else System.out.println("Номер "+input+" не найден");
    }
}
