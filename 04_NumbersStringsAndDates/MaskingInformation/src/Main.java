public class Main {

    public static void main(String[] args) {
   searchAndReplaceDiamonds("Номер кредитной карты <4008 1234 5678> 8912","***");

    }

    public static String searchAndReplaceDiamonds(String text, String placeholder) {


        int len = text.length();

        String bText = text.substring(0,text.lastIndexOf('<'));
        String eText = text.substring(text.lastIndexOf('>')+1,len);
        text = bText+placeholder+eText;
        System.out.println(text);

        return text;
    }

}