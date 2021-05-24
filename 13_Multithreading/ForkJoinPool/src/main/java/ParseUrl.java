import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParseUrl extends RecursiveAction {

  Logger logger = LogManager.getLogger();

  //Лист с внешними ссылками
  static volatile ArrayList<String> hyperlinks = new ArrayList<>();

  //Лист с внутренними ссылками
  static volatile ArrayList<String> links = new ArrayList<>();
  private String path;
  static BufferedWriter writer;

  //Инициализация writer
  static {
    {
      try {
        writer = new BufferedWriter(new FileWriter("result.txt", true));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public ParseUrl(String path) {
    this.path = path;
  }

  @Override
  protected void compute() {
    List<ParseUrl> taskList = new ArrayList<>();
    List<String> list = new ArrayList<>();
    try {
      Thread.sleep(200);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Document doc = null;
    String fullUrl;
    try {
      doc = Jsoup.connect(path).get();
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (doc != null) {
      Elements elements = doc.select("a");
      for (Element e : elements) {
        String url = e.attr("href");

        //Проверка на внутренние ссылки
        if (url.matches("/.+/")) {
          fullUrl = path + url;
          if (!hyperlinks.contains(fullUrl) & !links.contains(url)) {
            list.add("\t" + fullUrl);
            logger.info(fullUrl + " added to List.");
            hyperlinks.add(fullUrl);
            links.add(url);
          }
        }
        //Проверка на ссылки Скиллбокса
        if (url.contains("https://skillbox.ru")) {
          if (!hyperlinks.contains(url)) {
            list.add("\t" + url);
            logger.info(url + " added to List.");
            hyperlinks.add(url);
          }
        }
      }

      //Перебор ссылок страницы
      for (String url : list) {
        if (url.matches(".+/$")) {
          try {
            writer.newLine();
            writer.write(url);
            writer.flush();
            logger.info(url + " written to file.");
          } catch (IOException e) {
            e.printStackTrace();
          }

          //ForkJoinPool
          ParseUrl task = new ParseUrl(url);
          task.fork();
          logger.info(task + " is branched.");
          taskList.add(task);
          for (ParseUrl parseUrl : taskList) {
            parseUrl.join();
          }
        }
      }
    }
  }
}
