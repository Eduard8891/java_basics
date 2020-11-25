import BankClients.Client;
import BankClients.Entity;
import BankClients.Individual;
import BankClients.SelfEmployed;

public class Main {

    public static void main(String[] args) {


        Client individual = new Individual("4568895", 19000);
        System.out.println(individual.addMoney("4568895", 5000));
        System.out.println(individual.takeMoney("4568895",1000));
        System.out.println(individual.getBalance("4568895"));
        System.out.println("----------------------------------");

        Client entity = new Entity("4568885", 85000);
        System.out.println(entity.addMoney("4568885", 5000));
        System.out.println(entity.takeMoney("4568885",1000));
        System.out.println(entity.getBalance("4568885"));
        System.out.println("----------------------------------");

        Client selfEmployed = new SelfEmployed("4568279", 40000);
        System.out.println(selfEmployed.addMoney("4568279", 8000));
        System.out.println(selfEmployed.takeMoney("4568279",7500));
        System.out.println(selfEmployed.getBalance("4568279"));

    }
}
