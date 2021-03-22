import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.sql.SQLOutput;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parsing {

    public static void parseConnections(Document document) {
        Elements elements = document.select("a");
        for (Element element: elements) {
            String line = element.attr("title");
            if (!element.attr("title").isEmpty()) {
                if (line.contains("линия") | line.contains("станция") | line.contains("Переход")) {
//                    if (line.contains("станция монорельса")) break;
                    System.out.println(line);
                }
            }
        }
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
