import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        String filePath = parseFile("src/lenta.html");
        Document document = Jsoup.parse(filePath);
        Elements elements = document.select("img");
        for (Element i: elements) {
            downloadFile(i.absUrl("src"));
        }
    }

    public static String parseFile(String filePath) throws IOException {
        StringBuilder sb = new StringBuilder();
        List<String> list = Files.readAllLines(Path.of(filePath));
        list.forEach(line -> sb.append(line).append("\n"));
        return sb.toString();
    }

    public static void downloadFile(String url) throws IOException {
        InputStream in = null;
        if (!url.isEmpty()) {
                in = new URL(url).openStream();
                Files.copy(in, Path.of("files/"
                        + url.substring(url.length() - 5)), StandardCopyOption.REPLACE_EXISTING);

        }
    }
}
