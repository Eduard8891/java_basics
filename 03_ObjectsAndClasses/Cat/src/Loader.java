
public class Loader
{
    private static Cat getKitten () {
        Cat getKitten = new Cat(1100);
        return getKitten;
    }

    public static void main(String[] args)
    {
        Cat cat = new Cat();

        System.out.println(cat.getStatus());

        Cat lola = new Cat(1100);
        System.out.println("Лола весит: " + lola.getWeight());

        Cat dosya = new Cat(1100);
        System.out.println("Дося весит: " + dosya.getWeight());

        Cat busya = new Cat(1100);
        System.out.println("Буся весит: " + busya.getWeight());

        Cat pesik = new Cat();
        pesik.drink((double) 50);
        System.out.println("Песик весит: " + pesik.getWeight());

        Cat vinny = new Cat();
        vinny.pee();
        System.out.println("Винни весит: " + vinny.getWeight());

        Cat serega = new Cat();
        serega.meow();
        System.out.println("Серега весит: " + serega.getWeight());

        Cat kotopes = new Cat();
        kotopes.feed((double) 50);
        System.out.println("Котопес весит: " + kotopes.getWeight());

        Cat rediska = new Cat();
        System.out.println("Редиска весит: " + rediska.getWeight());
        while (rediska.getWeight() > 1000){

            rediska.meow();
        }
        System.out.println("Редиска весит: " + rediska.getWeight());
        System.out.println("Редиска весит: " + rediska.getStatus());

        Cat vovka = new Cat();
        System.out.println("Вовка весит: " + vovka.getWeight());
        while (vovka.getWeight() < 9000) {

            vovka.drink((double) 500);
            vovka.feed((double) 500);
        }
        System.out.println("Вовка весит: " + vovka.getWeight());
        System.out.println("Вовка весит: " + vovka.getStatus());
        System.out.println("Еда весит: " + Cat.weightFood);
        System.out.println("Количество живых кошек: " + Cat.getCount());
    }
}