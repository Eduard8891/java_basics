import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RedisStorage {
    static final String key = "Users";
    static final SimpleDateFormat DF = new SimpleDateFormat("HH:mm:ss");

    private static double getTs() {
        return new Date().getTime() / 1000;
    }


    void initialization(String host, int port) {
        try {
            Main.jedis = new Jedis(host, port);
        } catch (Exception e) {
            System.out.println("We have problems with Jedis!");
        }
    }

    List<User> userList() throws InterruptedException {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Thread.sleep(2);
            list.add(new User(i, getTs()));
        }
        return list;
    }


    void userOnMainPage(User user) {
        Main.jedis.zadd(key, getTs(), String.valueOf(user.getId()));
        System.out.printf("- На главной странице показываем пользователя: %d" + "\n", user.getId());
    }


}
