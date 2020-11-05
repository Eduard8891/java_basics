import java.text.DecimalFormat;

public class Main {

    static double averageTemp;
    static final int PATIENTS = 30;
    static final double MAX_TEMP = 40.00;
    static final double MIN_TEMP = 32.00;
    static final double MIN_NORMAL_TEMP = 36.00;
    static final double MAX_NORMAL_TEMP = 37.40;
    private static int n;


    public static void main(String[] args) {


        String text = "Каждый охотник желает знать, где сидит фазан";

        String [] colors = text.split(",?\\s+");

        for (int i = colors.length - 1; i >= 0; i--) {
            System.out.println(colors[i]);
        }
        System.out.println("=======================================================================");
        temperature();
        arrayList(20);
    }

    public static void temperature () {
        double [] patients = new double [PATIENTS];
        DecimalFormat decimalFormat = new DecimalFormat( "#.#" );
        System.out.println("Температуры пациентов:");
        int healthyPatients = 0;
        for (int i = 0; i <= PATIENTS-1; i++)
        {
            double temper = MIN_TEMP + ((MAX_TEMP - MIN_TEMP) * Math.random());
            patients [i] = temper;
            String result = decimalFormat.format(temper);
            System.out.print(decimalFormat.format(temper)+" | ");
            averageTemp += temper;
            if (temper <= MAX_NORMAL_TEMP && temper >= MIN_NORMAL_TEMP)
            {
                healthyPatients++;
            }
        }
        System.out.println("\n"+"Средняя температура по больнице: "+decimalFormat.format(averageTemp/PATIENTS));
        System.out.println("Количество здоровых пациентов: "+healthyPatients);
        System.out.println("=======================================================================");
    }

    public static void arrayList (int n) {


        String [][] eX = new String [n][n];

        for (int i = 0; i < eX.length; i++)
        {
            System.out.println();

            for (int k = 0; k < eX[i].length; k++)
            {
                if (i == k) {
                    System.out.print("x");
                }
                if (k == (eX[i].length-1)-i) {
                    System.out.print("x");
                }
                System.out.print(" ");

            }
        }
    }
}
