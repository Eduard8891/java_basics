import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество ящиков!");
        Integer box = scanner.nextInt();

        int cont = 1;
        int i = 0;
        int y = 0;
        int b = 1;
        int trucks = 1;

        System.out.println("Грузовик № "+trucks);
        System.out.println("\tКонтейнер № "+cont);
        System.out.println("\t\t\tЯщик № "+b);

        for ( b = 1; b <= box; b++) {
            i ++;
            y ++;
            if (y > 324){
                trucks ++;
                System.out.println("Грузовик №: "+trucks);
                y = 1;
            }
            if (i > 27){
                cont ++;
                System.out.println("\tКонтейнер №: "+cont);
                i = 1;
            }
            System.out.println("\t\t\tЯщик №: "+b);

        }
        float allC = (float) (box / 27.00);
        float allT = (float) (box / 324.00);
        int c = (int) Math.ceil(allC);
        int t = (int) Math.ceil(allT);

        System.out.println("Необходимо:");
        System.out.println("\tГрузовиков: "+t+" шт.");
        System.out.println("\tКонтейнеров: "+c+" шт.");
    }
}
