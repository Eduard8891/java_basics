import redis.clients.jedis.Jedis;

import java.util.List;


public class Main {
    static Jedis jedis = null;
    static String host = "127.0.0.1";
    static int port = 6379;
    static RedisStorage redisStorage;
    static List<User> list;

    static {
        redisStorage = new RedisStorage();
        redisStorage.initialization(host, port);
        try {
            list = redisStorage.userList();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            emulation();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void emulation() throws InterruptedException {
        Thread.sleep(100);
        while (true) {
            int id;
            for (; ; ) {
                double user = Math.random() * 10;
                id = (int) user;
                if (id != 0) break;
            }
            for (int i = 1; i <= 10; i++) {
                if (i == id) {
                    int random = (int) ((Math.random() * 20) + 1);
                    System.out.println("> Пользователь " + random + " оплатил платную услугу");
                    redisStorage.userOnMainPage(list.get(random));
                } else {
                    redisStorage.userOnMainPage(list.get(i));
                }
                Thread.sleep(100);
            }
            emulation();
        }
    }
}
