import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ФИО!");
        while (true) {
            String iinput = scanner.nextLine();
            String input = iinput.trim();
            if (input.length() > 5) {

                String fio = input.replaceAll("[^а-я,^А-Я]"," ");


                String family = fio.substring(0, fio.indexOf(' '));
                String familyT = family.trim();


                String name = fio.substring(input.indexOf(' '), fio.lastIndexOf(' '));
                String nameT = name.trim();

                int lNum = fio.length();
                String mName = fio.substring(fio.lastIndexOf(' '), lNum);
                String mNameT = mName.trim();

                if (familyT.length() >= 2) {
                    System.out.println("Фамилия: " + familyT);
                    if (nameT.length() >= 2) {
                        System.out.println("Имя: " + nameT);
                        if (mNameT.length() >= 2) {
                            System.out.println("Отчество: " + mNameT);
                        }
                    }
                }
                else {
                    System.out.println("Введенная строка не является ФИО");
                }

                break;
            }
        }
    }
}