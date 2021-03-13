import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class Movements {
    String type;
    String number;
    String currency;
    LocalDate date;
    String reference;
    String description;
    double receive;
    double spent;
    ArrayList<Movements> list = new ArrayList<>();

    public static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd.MM.yy", Locale.getDefault());

    public Movements(String pathMovementsCsv) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(pathMovementsCsv));
        reader.readLine();
        while (reader.ready()) {
            String allLine = reader.readLine();
            String[] line = fixLine(allLine).split(",", 8);
            list.add(new Movements(line[0], line[1], line[2],
                    LocalDate.parse(line[3], FORMAT), line[4], line[5], Double.parseDouble(line[6]), Double.parseDouble(line[7])));
        }
        reader.close();
        System.out.println(list.size());

    }


    public Movements(String type, String number, String currency, LocalDate date, String reference, String description, double receive, double spent) {
        this.type = type;
        this.number = number;
        this.currency = currency;
        this.date = date;
        this.reference = reference;
        this.description = description;
        this.receive = receive;
        this.spent = spent;
    }


    public double getExpenseSum() {
        double spent = 0;
        for (Movements i : list) {
            spent += i.spent;
        }
        return spent;
    }

    public double getIncomeSum() {
        double res = 0;
        for (Movements i : list) {
            res += i.receive;
        }
        return res;
    }

    public void details() {
        String company;
        int count = 0;
        System.out.println("Суммы расходов по организациям: приход/расход");
        for (Movements k : list) {
            String[] descript = k.description.split("(\\s{3})+");
            company = descript[1].trim();
            for (int i = company.length() - 1; i >= 0; i--) {

                if (company.charAt(i) == '\\' | company.charAt(i) == '/') {
                    count++;
                    System.out.println(count + ": " + company.substring((i) + 1) + "\t" + " | "
                            + k.receive + " / " + k.spent + " " + k.currency);
                    break;
                }
            }
        }
    }

    public String fixLine(String line) {
        int start;
        int finish;

        for (int i = 0; i <= line.length()-1; i++) {
            if (line.contains("\"")) {
                start = line.indexOf('"');
                for (int k = start+1; k < line.length(); k++) {
                    if (line.substring(k).contains("\"")) {
                        finish = line.substring(k).indexOf('"')+start;
                            String value = line.substring(start, finish).replaceAll(",", ".");
                            line = line.substring(0, start)+value+line.substring(finish);
                            line = line.replaceAll("\\\"", "");
                        }
                    }
                }
            return line.replaceAll("\\\"", "");
            }
        return line;
    }

}