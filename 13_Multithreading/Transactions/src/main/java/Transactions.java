public class Transactions extends Thread {

  private Bank bank;

  public Transactions(Bank bank) {
    this.bank = bank;
  }

  @Override
  public void run() {
    int countAccounts = bank.getAccounts().size();
    double numTo = countAccounts * Math.random();
    double numFrom = countAccounts * Math.random();
    double randomAmount = 52500 * Math.random();
    int amount = (int) randomAmount;
    int randomFrom = (int) numFrom;
    int randomTo = (int) numTo;
    int counter = 0;
    Account from = null;
    Account to = null;
    if (randomFrom != randomTo) {
      for (Account a : bank.getAccounts().values()) {
        if (counter == randomFrom) {
          from = a;
        }
        if (counter == randomTo) {
          to = a;
        }
        counter++;
      }
      if (from != null & to != null) {
        bank.transfer(from.getAccNumber(), to.getAccNumber(), amount);
      }
    }
  }
}
