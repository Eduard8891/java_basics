
public class Cat
{
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
        weight = weight - 200;
        System.out.println("Pee");
    }

    public double feed(Double amount) {
        weight = weight + amount;
        return weightFood = amount;
    }

    public void drink(Double amount) {
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

    static int getCount () {
        return count;
    }
}
