import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Bank {

  private int count;

  public Bank(int count) {
    this.count = count;
    generateAccounts(count);
  }

  public Map<String, Account> getAccounts() {
    return accounts;
  }

  private Map<String, Account> accounts = new HashMap<>();
  private Map<String, Account> banned = new HashMap<>();
  private final Random random = new Random();

  public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
      throws InterruptedException {
    Thread.sleep(1000);
    return random.nextBoolean();
  }

  /**
   * TODO: реализовать метод. Метод переводит деньги между счетами. Если сумма транзакции > 50000,
   * то после совершения транзакции, она отправляется на проверку Службе Безопасности – вызывается
   * метод isFraud. Если возвращается true, то делается блокировка счетов (как – на ваше
   * усмотрение)
   */
  public void transfer(String fromAccountNum, String toAccountNum, long amount) {
    if (accounts.containsKey(fromAccountNum) && accounts.containsKey(toAccountNum)) {
      Account from = accounts.get(fromAccountNum);
      Account to = accounts.get(toAccountNum);
      if (from.getMoney() > amount) {
        if (amount > 50000) {
          try {
            if (isFraud(fromAccountNum, toAccountNum, amount)) {
              banned.put(fromAccountNum, from);
              banned.put(toAccountNum, to);
              accounts.remove(fromAccountNum);
              accounts.remove(toAccountNum);
              System.out.println("Accounts with accNumbers : "
                  + fromAccountNum + " and " + toAccountNum + " was banned!");
              return;
            }
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        from.setMoney(from.getMoney() - amount);
        to.setMoney(to.getMoney() + amount);
        System.out.println("Transaction from " + fromAccountNum + " to "
            + toAccountNum + " in the amount of " + amount + " was completed!");
      }
    }
  }

  public long getBalance(String accountNum) {
    return accounts.get(accountNum).getMoney();
  }

  public long getSumAllAccounts() {
    long sumAllAccounts = 0;
    for (Account account : accounts.values()) {
      sumAllAccounts += account.getMoney();
    }
    return sumAllAccounts;
  }

  private void generateAccounts(int count) {
    for (int i = 0; i < count; i++) {
      String accNumber = String.valueOf(1326598 * Math.random()).replace(".", "");
      accNumber = "ac" + accNumber;
      String money = String.valueOf(1000 * Math.random()).replace(".", "");
      long accMoney = Integer.parseInt(money.substring(0, 6));
      accounts.put(accNumber, new Account(accMoney, accNumber));
    }
  }
}
