import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ArrayList<String> toDoList = new ArrayList<>() {{
            add("Помыть полы");
            add("Приготовить поесть");
            add("Сходить в магазин");
            add("Выгулять тигра");
        }};
        System.out.println("Список команд: \nADD №(или без): Добавить дело №\nEDIT №(или без): Редактировать дело №" +
                "\nDELETE №: Удалить дело № \nLIST: Список всех дел");
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String toDo = scanner.nextLine();
            String[] splitThree = toDo.split(" ", 3);
            String[] splitTwo = toDo.split(" ", 2);

            if (splitThree[0].equals("ADD")) {
                if (splitThree[1].matches("\\d+")  && Integer.parseInt(splitThree[1]) >= 0) {

                    if (Integer.parseInt(splitThree[1]) > toDoList.size()) {
                        toDoList.add(splitThree[2]);
                        System.out.println("Дело добавлено!");
                        continue;
                    }

                    else {
                        toDoList.add(Integer.parseInt(splitThree[1]),splitThree[2]);
                        System.out.println("Дело добавлено!");
                    }
                }
                if (splitThree[1].matches("\\w+")) {
                    toDoList.add(splitTwo[1]);
                    System.out.println("Дело добавлено!");
                }
            }

            if (splitThree[0].equals("EDIT")) {
                if (Integer.parseInt(splitThree[1]) < toDoList.size() && Integer.parseInt(splitThree[1]) >= 0) {
                    toDoList.set(Integer.parseInt(splitThree[1]), splitThree[2]);
                    System.out.println("Дело " + Integer.parseInt(splitThree[1]) + " отредактировано!");
                } else System.out.println("Неверный номер дела! Попробуйте еще раз!");
            }

            if (splitThree[0].equals("DELETE")) {
                if (Integer.parseInt(splitThree[1]) < toDoList.size() && Integer.parseInt(splitThree[1]) >= 0) {
                    toDoList.remove(Integer.parseInt(splitThree[1]));
                    System.out.println("Дело " + Integer.parseInt(splitThree[1]) + " удалено!");
                } else System.out.println("Неверный номер дела! Попробуйте еще раз!");
            }

            if (toDo.equals("LIST")) {
                for (int i = 0; i < toDoList.size(); i++) {
                    System.out.println(i + " - " + toDoList.get(i));
                }
            }
        }
    }
}
