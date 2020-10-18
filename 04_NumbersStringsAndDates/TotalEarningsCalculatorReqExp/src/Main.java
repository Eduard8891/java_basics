public class Main {

    public static void main(String[] args) {

        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";

        String textRep = text.replaceAll("[^0-9]"," ");
        String[] sums = textRep.split("\\s+");
        int sumE; int b=0;
        for (int i=1; i < sums.length; i++){
            sumE = Integer.parseInt(sums[i]);
            b = sumE+b;
        }
        System.out.println("Общая сумма заработка = "+b);
    }
}