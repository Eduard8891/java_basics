
public class Cat
{
    public static final int EYES_COUNT = 2;
    public static final int MIN_WEIGHT = 1000;
    public static final int MAX_WEIGHT = 9000;
    private double originWeight;
    private double weight;
    private double minWeight;
    private double maxWeight;
    static double weightFood;
    static int count = 0;

    private boolean isAlive() {
        return weight >= MIN_WEIGHT && weight <= MAX_WEIGHT;
    }

    private void decreaseCountIfDead() {
        if (!isAlive()) {
            count--;
        }
    }

    public Cat() {
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        minWeight = 1000.0;
        maxWeight = 9000.0;
        count ++;

    }
    public Cat (double weight)
    {
        this();
        this.weight = weight;
        count ++;
    }

    public void meow() {
        if (isAlive()) {
            weight = weight - 100;
            System.out.println("Meow");
            decreaseCountIfDead();
        }
    }

    public void pee() {
        if (isAlive()) {
            weight = weight - 200;
            System.out.println("Pee");
            decreaseCountIfDead();
        }
    }

    public void feed(Double amount) {
        if (isAlive()) {
            weight = weight + amount;
            weightFood = weightFood + amount;
            System.out.println("Feed");
            decreaseCountIfDead();
        }
    }

    public void drink(Double amount) {
        if (isAlive())
        weight = weight + amount;
        System.out.println("Drink");
    }

    public Double getWeight() {
        return weight;
    }

    public String getStatus()
    {
        if(weight < minWeight) {
            count --;
            return "Dead";
        }
        else if(weight > maxWeight) {
            count --;
            return "Exploded";
        }
        else if(weight > originWeight) {
            return "Sleeping";
        }
        else {
            return "Playing";
        }
    }

    public static int getCount () {
        return count;
    }

}
