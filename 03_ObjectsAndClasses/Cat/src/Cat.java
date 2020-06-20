
public class Cat
{
    public static final int EYES_COUNT = 2;
    public static final int MIN_WEIGHT = 100;
    public static final int MAX_WEIGHT = 10000;
    private double originWeight;
    private double weight;
    private double minWeight;
    private double maxWeight;
    static double weightFood;
    static int count = -1;

    public Cat() {
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        minWeight = 1000.0;
        maxWeight = 9000.0;
        count ++;

    }

    public void meow() {
        weight = weight - 100;
        System.out.println("Meow");
    }

    public void pee() {
        if (weight < 9000 || weight < 1000)
        weight = weight - 200;
        System.out.println("Pee");
    }

    public double feed(Double amount) {
        if (weight < 9000 || weight < 1000)
        weight = weight + amount;
        return weightFood = weightFood + amount;
    }

    public void drink(Double amount) {
        if (weight < 9000 || weight < 1000)
        weight = weight + amount;
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
