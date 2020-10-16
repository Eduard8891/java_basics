public class Main {
    public static void main(String[] args) {
        String alphabetEn = "abcdefghijklmnopqrstvwxyzABCDEFGHIGKLMNOPQRSTVWXYZ";
        int enL = alphabetEn.length();

        for (int i = 0; i <= enL; i++){
            System.out.print(alphabetEn.charAt(i)+"  ");
            char c = alphabetEn.charAt(i);
            int code = (int) c;
            System.out.println(code);
        }

    }
}
