import java.util.concurrent.ForkJoinPool;

public class Main {
  /*
  Сначала получение всех ссылок страницы и добавление их в лист, потом перебор,
  запись в файл и рекурсивное выполенине каждой.
   */

  static String url = "https://skillbox.ru";

  public static void main(String[] args) {

    new ForkJoinPool().invoke(new ParseUrl(url));
  }
}

