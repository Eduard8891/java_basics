
public class Loader
{
    public static void main(String[] args)
    {
        Cat cat = new Cat();

        System.out.println(cat.getStatus());

        Cat pesik = new Cat();
        pesik.drink((double) 50);
        System.out.println("Песик весит: " + pesik.getWeight());

        Cat vinny = new Cat();
        vinny.meow();
        System.out.println("Винни весит: " + vinny.getWeight());

        Cat serega = new Cat();
        serega.meow();
        System.out.println("Серега весит: " + serega.getWeight());

        Cat kotopes = new Cat();
        kotopes.feed((double) 50);
        System.out.println("Котопес весит: " + kotopes.getWeight());

        Cat rediska = new Cat();
        System.out.println("Редиска весит: " + rediska.getWeight());
        rediska.meow();
        rediska.meow();
        rediska.meow();
        System.out.println("Редиска весит: " + rediska.getWeight());
        System.out.println("Редиска весит: " + rediska.getStatus());

        Cat vovka = new Cat();
        System.out.println("Вовка весит: " + vovka.getWeight());
        vovka.drink((double) 500);
        vovka.feed((double) 5000);
        vovka.drink((double) 500);
        System.out.println("Вовка весит: " + vovka.getWeight());
        System.out.println("Вовка весит: " + vovka.getStatus());
    }
}