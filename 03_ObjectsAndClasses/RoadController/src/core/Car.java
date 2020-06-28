package core;

public class Car
{
    public String number;
    public int height;
    public double weight;
    public boolean vehicle;
    public boolean special;

    public void setNumber (String number) {
        this.number = number;
    }

    public String getNumber () {
        return number;
    }

    public void setHeight (int height) {
        this.height = height;
    }

    public int getHeight () {
        return height;
    }

    public void setWeight (double weight) {
        this.weight = weight;
    }

    public double getWeight () {
        return weight;
    }

    public boolean hasVehicle() {
        return vehicle;
    }

    public void setHasVehicle(boolean vehicle) {
        this.vehicle = vehicle;
    }

    public boolean isSpecial() {
        return special;
    }

    public void setSpecial(boolean special) {
        this.special = special;
    }

    public String toString()
    {
        String special = isSpecial() ? "СПЕЦТРАНСПОРТ " : "";
        return "\n=========================================\n" +
            special + "Автомобиль с номером " + getNumber() +
            ":\n\tВысота: " + getHeight() + " мм\n\tМасса: " + getWeight() + " кг";
    }
}