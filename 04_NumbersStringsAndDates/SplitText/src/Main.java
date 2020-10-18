public class Main {

    public static void main(String[] args) {
        splitTextInToWords("Millions across Europe faced greater restrictions on their movement over the weekend, with Italy readying new measures and London and Paris enforcing tighter curbs, in an escalating effort to check the surge in coronavirus cases. Italian Prime Minister Giuseppe Conte may order bars and restaurants to close at 10 p.m., ban some sporting activities and change the hours for high schools to prevent congestion, according to officials in his government, who asked not to be identified in line with their policy. He is set to announce his plans on Sunday. In London, new rules took effect on Saturday banning people from mixing with other households indoors, while in Paris and eight other French cities, residents are now confined to home between 9 p.m. and 6 a.m. for four weeks. In Belgium, which has the most cases per-capita in Europe except for the Czech Republic, the government announced plans for new restrictions nationwide starting on Monday.");

    }

    public static String splitTextInToWords(String text) {
        String textC = text.replaceAll("[0-9]"," ");
        String textX = textC.replace(","," ");
        String textY = textX.replace("."," ");
        String[] textEnd = textY.split("\\s+");
        for (int i = 0; i < textEnd.length; i++) {
            System.out.println(textEnd[i]);
        }
        return text;
    }

}