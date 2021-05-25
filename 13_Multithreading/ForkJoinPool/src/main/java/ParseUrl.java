import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.RecursiveTask;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ParseUrl extends RecursiveTask<String> {
  private final static List<String> urlList = new Vector<>();

  private final String url;
  private final String prefix;

  public ParseUrl (String url, String prefix) {
    this.url = url;
    this.prefix = prefix;
  }

  @Override
  protected String compute() {
    StringBuilder result = new StringBuilder();
    result.append(url);
    try {
      Thread.sleep(200);
      Document doc = Jsoup.connect(url).maxBodySize(0).get();
      Elements rootElements = doc.select("a");

      List<ParseUrl> linkGrabers = new ArrayList<>();
      rootElements.forEach(element -> {
        String link = element.attr("abs:href");
        if (link.startsWith(element.baseUri())
            && !link.equals(element.baseUri())
            && !link.contains("#")
            && !link.contains(".pdf")
            && !urlList.contains(link)
        ) {
          urlList.add(link);
          ParseUrl linkGraber = new ParseUrl(link,prefix+"\t");
          linkGraber.fork();
          linkGrabers.add(linkGraber);
        }
      });

      for (ParseUrl lg : linkGrabers) {
        String text = lg.join();
        if (!text.equals("")) {
          result.append("\n");
          result.append(prefix);
          result.append(text);
        }
      }
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
    return result.toString();
  }
}