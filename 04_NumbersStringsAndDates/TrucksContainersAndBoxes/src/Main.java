public class Main {
    public static void main(String[] args) {

        int box = 550;


        int cont = 1;
        int i = 0;
        int y = 0;
        int trucks = 1;

        for (int b = 1; b <= box; b++) {
            i ++;
            y ++;
            if (i > 27){
                cont ++;
                i = 1;
            }
            if (y > 324){
                trucks ++;
                y = 1;
            }
            System.out.println("В грузовике "+trucks+" находится контейнер "+cont+" и ящик номер "+b);

        }
    }
}
