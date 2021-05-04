public class Main {

  public static void main(String[] args) {

    Bank bank = new Bank(150);
    for (int i = 0; i < 1000; i++) {
      new Transactions(bank).start();
    }
  }
}
