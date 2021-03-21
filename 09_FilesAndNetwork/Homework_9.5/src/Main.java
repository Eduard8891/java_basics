import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.List;

public class Main {
    static HashMap <String, String> colors = new HashMap<>();
    private static String jsonPath = "src/map.json";
    static {
    putColor();
    }
    static ArrayList <Station> stations = new ArrayList<>();
    static ArrayList <Line> lines = new ArrayList<>();
    static ArrayList <Connection> connections = new ArrayList<>();
    static HashMap <String, String> st = new HashMap<>();

    public static void main(String[] args) throws IOException, ParseException {

        String filePath = parseFile("src/metro.html");
        Document document = Jsoup.parse(filePath);
        parseLine(document);
        parseStation(document);
        createGSonFile();
        parseJson();
    }

    public static void parseJson() throws ParseException, IOException {
        ArrayList <Integer> stationsValues = new ArrayList<>();
        JSONParser parser = new JSONParser();
        Object object = parser.parse(new FileReader(jsonPath));
        JSONObject jsonObject = (JSONObject) object;
        JSONObject parseStations = (JSONObject) jsonObject.get("stations");

            for (Object parseValue: parseStations.values()) {
                String temp = parseValue.toString()
                        .replaceAll("\"", "");
                String[] numValues = temp.split(",");
                stationsValues.add(numValues.length);
            }
            int count = 0;
            for (Object parseLines: parseStations.keySet()) {
                System.out.println(parseLines.toString() + ": " + stationsValues.get(count) + " станций");
                count++;
            }
    }

    public static void createGSonFile() throws IOException {
        JSONObject generalObject = new JSONObject();
        JSONObject stationsOnLine = new JSONObject();

        for (int i = 0; i < lines.size(); i++) {              //Stations
            JSONArray jArrayStations = new JSONArray();
            for (Station st: stations) {
                if (st.getLine().getNumber() == lines.get(i).getNumber())
                    jArrayStations.add(st.getName());
            }
            stationsOnLine.put(lines.get(i).getName(), jArrayStations);
        }

        JSONArray jArrayLines = new JSONArray();              //Lines
        for (Line l: lines) {
            JSONObject jObj = new JSONObject();
            jObj.put("Number", l.getNumber());
            jObj.put("Name", l.getName());
            jObj.put("Color", l.getColor());
            jArrayLines.add(jObj);
            generalObject.put("Lines", jArrayLines);
        }
        generalObject.put("stations", stationsOnLine);

        FileWriter writer = new FileWriter(jsonPath);
        writer.write(generalObject.toString());
        writer.flush();
    }

    public static void parseLine(Document document) {
        Elements elements = document.select("td");
        String temp = "";
        for (Element i: elements) {
            if (!i.attr("style").isEmpty() & !i.attr("data-sort-value").isEmpty()) {
                String numLine = i.toString().replaceAll("\\D", " ").substring(90, 100).trim();
                String lineName = i.select("span").attr("title");
                if (!temp.equals(lineName)) {
                    lines.add(new Line(Integer.parseInt(numLine), lineName, getColor(lineName)));
                    temp = lineName;
                }
            }
        }
    }

    public static void parseStation(Document document) {
        Elements elements = document.select("a");
        String temp = "";
        int index = 0;
        for (Element i: elements) {
            if (!i.attr("title").isEmpty()) {
                String line = i.attr("title");
                if (line.contains("линия") | line.contains("станция")) {
                    if (line.contains("монорельса")) break;
                    if (line.contains("станция метро")) {
                        for (int a = 0; a < line.length(); a++) {
                            if (line.charAt(a) == '(') {
                                String nameSt = line.substring(0, a-1);
                                stations.add(new Station(nameSt, lines.get(index)));
                            }
                        }
                    }
                    if (line.contains("линия") & !line.equals(temp) & !line.contains("станция")) {
                        temp = line;
                        for (int b = 0; b < lines.size(); b++)
                            if (lines.get(b).getName().equals(temp)) index = b;
                    }
                }
            }
        }
    }

    public static String parseFile(String filePath) throws IOException {
        StringBuilder sb = new StringBuilder();
        List<String> list = Files.readAllLines(Path.of(filePath));
        list.forEach(line -> sb.append(line).append("\n"));
        return sb.toString();
    }

    public static String getColor(String line) {
        for (Map.Entry i: colors.entrySet()) {
            if (line.equals(i.getKey())) return i.getValue().toString();
        }
        return null;
    }

    public static void putColor() {
        colors.put("Сокольническая линия", "Красный");
        colors.put("Замоскворецкая линия", "Зеленый");
        colors.put("Арбатско-Покровская линия", "Синий");
        colors.put("Филёвская линия", "Голубой");
        colors.put("Кольцевая линия", "Коричневый");
        colors.put("Калужско-Рижская линия", "Оранжевый");
        colors.put("Таганско-Краснопресненская линия", "Фиолетовый");
        colors.put("Солнцевская линия", "Желтый");
        colors.put("Калининская линия", "Желтый");
        colors.put("Серпуховско-Тимирязевская линия", "Серый");
        colors.put("Люблинско-Дмитровская линия", "Салатовый");
        colors.put("Большая кольцевая линия", "Бирюзовый");
        colors.put("Бутовская линия", "Серо-голубой");
        colors.put("Некрасовская линия", "Розовый");
    }
}
