import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.Map;
public class Parsing {

    public static void parseConnections(Document document)  {
        Elements elements = document.select("span");
        String line1 = "";
        String temp = "";
        String line2 = "";
        String line3 = "";
        int count = 0;
        for (Element i: elements) {
            String line = i.select("a").attr("title");
            if (!line.isEmpty()) {
                    if (line.contains("Переход") & !line3.equals(line)) {
                        line3 = line.substring(19);
                        if (line3.contains("линии")) line3 = line3.substring(0, line3.length()-6);
                        for (Line l: Main.lines) {
                            if (l.getName().contains(line1)) {
                                for (Station s: Main.stations) {
                                    if (s.getName().equals(line2))
                                        if (stationReturn(line3) != null & lineReturn(line3) != null)
                                    Main.connections.add(new Connection(l, s, lineReturn(line3), stationReturn(line3)));
                                }
                            }
                        }
                    }
                    if (Main.colors.containsKey(line) & count == 0) {
                        line1 = line;
                        count = 5;
                    }
                    else if (count == 5) {
                        temp = line;
                        count = 0;
                        if (temp.contains("(")) {
                            for (int a = 0; a < temp.length(); a++) {
                                if (temp.charAt(a) == '(') {
                                    line2 = temp.substring(0, a - 1);
                                }
                            }
                        }
                    }
            }
            if (line.contains("Некрасовка (станция метро)")) break;
        }
    }

    public static Station stationReturn(String stationName) {
        for (Station station: Main.stations) {
            if (stationName.contains(station.getName()))
                return station;

        }
        return null;
    }

    public static Line lineReturn(String lineName) {
        if (lineName.contains("Большой кольцевой") | lineName.contains("Большая кольцевой")) return Main.lines.get(11);
        if (lineName.contains("кольца")) return Main.lines.get(14);
        if (lineName.contains("монорельса")) return Main.lines.get(13);
        else {
            String [] split = lineName.split(" ");
            for (Line line: Main.lines) {
                String lineStart = line.getName().substring(0, 5);
                String input = split[split.length-1].substring(0, 5);
                if (lineStart.equals(input))
                return line;
            }
        }
        return null;
    }

    public static void parseStation(Document document) {
        Elements elements = document.select("td");
        String line1 = "";
        String line2 = "";
        String temp = "";
        int count = 0;
        for (Element i: elements) {
            String line = i.select("a").attr("title");
            if (!line.isEmpty()) {
                if (!line.contains("Односводчатая") & !line.contains("Колонная") & !line.contains("Пилонная")) {
                    if (Main.colors.containsKey(line) & count == 0) {
                        line1 = line;
                        count = 5;
                    }
                    else if (count == 5) {
                        line2 = line;
                        count = 0;

                        if (line2.contains("(")) {
                            for (int a = 0; a < line2.length(); a++) {
                                if (line2.charAt(a) == '(') {
                                    temp = line2.substring(0, a - 1);
                                }
                            }
                        }
                        for (Line lineCont: Main.lines) {
                            if (lineCont.getName().equals(line1))
                                Main.stations.add(new Station(temp, lineCont));
                        }
                    }
                }
            }
            if (line.contains("Лихоборы (станция МЦК)")) break;
        }
    }

    public static void parseLine(Document document) {
        Elements elements = document.select("a");
        String temp = "";
        for (Element i : elements) {
            String linePars = i.select("img").attr("alt");
            if (!linePars.isEmpty() & Main.colors.containsKey(linePars)) {
                String num = i.select("img").attr("src").replaceAll("\\D", " ").trim();
                num = num.substring(num.length()-5).trim();
                if (num.length()<3) {
                    if (!temp.equals(linePars)) {
                        Main.lines.add(new Line(Integer.parseInt(num), linePars, getColor(linePars)));
                        temp = linePars;
                        if (Main.lines.size() - 1 == 15) break;
                    }
                }
            }
        }
    }


    public static String getColor(String line) {
        for (Map.Entry i: Main.colors.entrySet()) {
            if (line.equals(i.getKey())) return i.getValue().toString();
        }
        return null;
    }

    public static void putColor() {
        Main.colors.put("Сокольническая линия", "Красный");
        Main.colors.put("Замоскворецкая линия", "Зеленый");
        Main.colors.put("Арбатско-Покровская линия", "Синий");
        Main.colors.put("Филёвская линия", "Голубой");
        Main.colors.put("Кольцевая линия", "Коричневый");
        Main.colors.put("Калужско-Рижская линия", "Оранжевый");
        Main.colors.put("Таганско-Краснопресненская линия", "Фиолетовый");
        Main.colors.put("Солнцевская линия", "Желтый");
        Main.colors.put("Калининская линия", "Желтый");
        Main.colors.put("Серпуховско-Тимирязевская линия", "Серый");
        Main.colors.put("Люблинско-Дмитровская линия", "Салатовый");
        Main.colors.put("Большая кольцевая линия", "Бирюзовый");
        Main.colors.put("Бутовская линия", "Серо-голубой");
        Main.colors.put("Московский монорельс", "Синий с уменьшенной толщиной");
        Main.colors.put("Московское центральное кольцо", "Белый с красной окантовкой");
        Main.colors.put("Некрасовская линия", "Розовый");
    }
}
