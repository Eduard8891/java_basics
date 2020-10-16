public class Main {

    public static void main(String[] args) {

        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";

        int fIndex = text.indexOf('5');
        int flIndex = fIndex + 4;
        String fNum = text.substring(fIndex,flIndex);

        int nlIndex = text.lastIndexOf('0');
        int lastIndex = nlIndex + 1;
        int flsIndex = lastIndex - 6;
        String dNum = text.substring(flsIndex,lastIndex);
        String sNum = dNum.trim();

        int a = Integer.parseInt(fNum);
        int b = Integer.parseInt(sNum);

        System.out.println(a+b);
    }

}