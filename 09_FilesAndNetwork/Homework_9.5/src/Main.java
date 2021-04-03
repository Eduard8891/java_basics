import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        wikiMskMetroJSON doc = new wikiMskMetroJSON();
        doc.parseFile("src/metro.html");
        JSONObject obj = new JSONObject();
        obj.put("connections", doc.connections);
        obj.put("stations", doc.stations);
        obj.put("lines", doc.lines);
        FileWriter file = new FileWriter("src/map.json");
        obj.writeJSONString(file);
        file.flush();
        file.close();

        JSONParser parser = new JSONParser();
        Reader reader = new BufferedReader(new FileReader("src/map.json"));
        Object object = parser.parse(reader);
        JSONObject parsingObj = (JSONObject) object;
        Object connections = parsingObj.get("connections");
        String [] connect = connections.toString().split("]");
        System.out.println("Количество переходов: " + connect.length);
    }

}
