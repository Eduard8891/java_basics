import java.util.ArrayList;

public class Main {

  public static void main(String[] args) {

    int countAccounts = 10;
    int countThreads = 1000;

    Bank bank = new Bank(countAccounts);
    for (int i = 0; i < countThreads; i++) {
      new Transactions(bank).start();
    }
  }

}
