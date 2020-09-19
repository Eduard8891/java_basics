import java.awt.Color;

public class Loader
{
    private static Cat getKitten () {
        Cat getKitten = new Cat(1100);
        return getKitten;
    }


    public static void main(String[] args)
    {
        Cat cat = new Cat();
        cat.setColor(Color.WHITE);


        System.out.println(cat.getStatus());

        Cat lola = new Cat(1100);
        System.out.println("Лола весит: " + lola.getWeight());

        Cat dosya = new Cat(1100);
        System.out.println("Дося весит: " + dosya.getWeight());

        Cat busya = new Cat(1100);
        System.out.println("Буся весит: " + busya.getWeight());
        System.out.println("================================");

        Cat pesik = new Cat();
        pesik.setName("Pesik");
        pesik.setWeight(2500);
        pesik.setColor(Color.BLACK);
        System.out.println("Имя кота : " + pesik.getName());
        System.out.println("Вес кота: " + pesik.getWeight());
        System.out.println("Цвет кота : " + pesik.getColor());

        Cat copyPesik = new Cat();
        copyPesik.setName(pesik.getName());
        copyPesik.setWeight(pesik.getWeight());
        copyPesik.setColor(Color.BLACK);
        System.out.println("Имя кота : " + copyPesik.getName());
        System.out.println("Вес кота: " + copyPesik.getWeight());
        System.out.println("Цвет кота : " + copyPesik.getColor());
        System.out.println("================================");

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
        while (rediska.getWeight() > Cat.MIN_WEIGHT){

            rediska.meow();
        }
        System.out.println("Редиска весит: " + rediska.getWeight());
        System.out.println("Редиска весит: " + rediska.getStatus());

        Cat vovka = new Cat();
        System.out.println("Вовка весит: " + vovka.getWeight());
        while (vovka.getWeight() < Cat.MAX_WEIGHT) {

            vovka.drink((double) 500);
            vovka.feed((double) 500);
        }
        System.out.println("Вовка весит: " + vovka.getWeight());
        System.out.println("Вовка весит: " + vovka.getStatus());
        System.out.println("Еда весит: " + Cat.weightFood);
        System.out.println("Количество живых кошек: " + Cat.getCount());
    }
}