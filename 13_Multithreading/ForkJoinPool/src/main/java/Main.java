import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.ForkJoinPool;

public class Main {
  /*
  Сначала получение всех ссылок страницы и добавление их в лист, потом перебор,
  запись в файл и рекурсивное выполенине каждой.
   */

  static String url = "https://skillbox.ru";

  public static void main(String[] args) {

    String text = new ForkJoinPool().invoke(new ParseUrl(url, "\t"));
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter("result.txt"));
      writer.write(text);
      writer.flush();
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}

