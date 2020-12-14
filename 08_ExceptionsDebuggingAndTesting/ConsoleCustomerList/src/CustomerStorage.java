import java.util.HashMap;

public class CustomerStorage
{
    private HashMap<String, Customer> storage;

    public CustomerStorage()
    {
        storage = new HashMap<>();
    }

    public void addCustomer(String data)
    {
        try {
            String[] components = data.split("\\s+");
            if (components.length == 4) {
                if (components[2].matches("^[A-Za-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$")) {
                    if (components[3].matches("[+]+[0-9]+")) {
                        String name = components[0] + " " + components[1];
                        storage.put(name, new Customer(name, components[3], components[2]));
                    }
                    else throw new IllegalArgumentException("You entered invalid phone number! Try again.");
                } else throw new IllegalArgumentException("You entered invalid email! Try again.");
            } else throw new IllegalArgumentException("You entered extra data! Try again.");
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void listCustomers()
    {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name)
    {
        if (storage.containsKey(name)) {
            storage.remove(name);
        } else throw new IllegalArgumentException("Sorry, this name this not on the list!");
    }

    public int getCount()
    {
        return storage.size();
    }
}