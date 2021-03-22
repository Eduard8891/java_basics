import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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
    Parsing.putColor();
    }
    static ArrayList <Station> stations = new ArrayList<>();
    static ArrayList <Line> lines = new ArrayList<>();
    static ArrayList <Connection> connections = new ArrayList<>();
    static HashMap <String, String> st = new HashMap<>();

    public static void main(String[] args) throws IOException, ParseException {

        String filePath = parseFile("src/metro.html");
        Document document = Jsoup.parse(filePath);
        Parsing.parseLine(document);
        Parsing.parseStation(document);
//        Parsing.parseConnections(document);
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


    public static String parseFile(String filePath) throws IOException {
        StringBuilder sb = new StringBuilder();
        List<String> list = Files.readAllLines(Path.of(filePath));
        list.forEach(line -> sb.append(line).append("\n"));
        return sb.toString();
    }
}
