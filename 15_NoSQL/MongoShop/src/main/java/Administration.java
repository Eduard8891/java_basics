import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Sorts.descending;
import static com.mongodb.client.model.Sorts.ascending;

import java.util.Arrays;
import java.util.Scanner;

public class Administration {

    private MongoCollection<Document> products;
    private MongoCollection<Document> shops;


    public void scan() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine();
            if (line.matches("ДОБАВИТЬ_МАГАЗИН [А-ЯЁ][а-яё]+")) {
                String[] lines = line.split(" ", 2);
                addShop(lines[1]);
            } else if (line.matches("ДОБАВИТЬ_ТОВАР [А-ЯЁ][а-яё]+ \\d+")) {
                String[] lines = line.split(" ", 3);
                addProduct(lines[1], Integer.parseInt(lines[2]));
            } else if (line.matches("ВЫСТАВИТЬ_ТОВАР [А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+")) {
                String[] lines = line.split(" ", 3);
                displayProduct(lines[1], lines[2]);
            } else if (line.matches("СТАТИСТИКА_ТОВАРОВ")) {
                productStats();
            } else System.out.println("ОШИБКА! ПОПРОБУЙТЕ ЕЩЕ РАЗ!");
        }
    }

    public void productStats() {
        System.out.println("Общее количество товаров: " + products.countDocuments());


        AggregateIterable<Document> aggregateIterable = products.aggregate(Arrays.asList(
                Document.parse("{ $group: { _id: null, averagePrice: { $avg: '$Price' } } }")
        ));
        for (Document document : aggregateIterable) {
            System.out.println("Средняя цена всех товаров: " + document.get("averagePrice"));
        }

        System.out.print("Самый дорогой товар: ");
        Document max = products.find().sort(descending("Price")).first();
        System.out.println(max.get("Title"));

        System.out.print("Самый дешевый товар: ");
        Document min = products.find().sort(ascending("Price")).first();
        System.out.println(min.get("Title"));

        BasicDBObject query = new BasicDBObject();
        query.put("Price", new BasicDBObject("$lt", 100));
        FindIterable<Document> findIterable = products.find(query);
        int count = 0;
        for (Document document : findIterable) {
            count++;
        }
        System.out.println("Количество товаров дешевле 100 рублей: " + count);
    }

    public boolean addShop(String name) {
        shops.insertOne(new Document("Name", name));
        Iterable<Document> thatShop = shops.find(new BasicDBObject("Name", name));
        for (Document document : thatShop) {
            if (document.isEmpty()) {
                return false;
            }
            System.out.println("добавлено:" + document.get("Name"));
        }
        return true;
    }

    public boolean addProduct(String title, int price) {
        products.insertOne(new Document("Title", title).append("Price", price));
        Iterable<Document> thatProd = products.find(new BasicDBObject("Title", title));
        for (Document document : thatProd) {
            if (document.isEmpty()) {
                return false;
            }
            System.out.println("добавлено:" + document.get("Title"));
        }
        return true;
    }

    public boolean displayProduct(String title, String name) {
        Iterable<Document> thatProd = products.find(new BasicDBObject("Title", title));
        Iterable<Document> thatShop = shops.find(new BasicDBObject("Name", name));
        Document shop = null;
        for (Document document : thatProd) {
            if (document == null) {
                return false;
            }
        }
        for (Document document : thatShop) {
            if (document == null) {
                return false;
            }
            shop = document;
        }
        shops.deleteOne(new BasicDBObject("Name", name));
        if (shop.get("Products") == null) {
            shops.insertOne(new Document("Name", name).append("Products", title));
        } else {
            shops.insertOne(new Document("Name", name).append("Products", title + ", " + shop.get("Products")));
        }

        Iterable<Document> shopF = shops.find(new BasicDBObject("Name", name));
        for (Document document : shopF) {
            System.out.println(document);
        }
        return true;
    }


    public void connect() {
        MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
        MongoDatabase database = mongoClient.getDatabase("local");
        shops = database.getCollection("shops");
        products = database.getCollection("products");
        shops.drop();
        products.drop();
    }

}
