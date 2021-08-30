
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.descending;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Main {
    static String path = "src/main/resources/mongo.csv";
    static List<Student> studentsList = new ArrayList<>();
    static List<String> courses = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        try {
            parse();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            writeAndFind();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void parse() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        Set<String> set = new HashSet<>();
        StringBuilder stringBuilder = new StringBuilder();
        while (bufferedReader.ready()) {
            String line = bufferedReader.readLine();
            if (!line.isEmpty()) {
                String[] student = line.split(",", 3);
                String studentName = student[0];
                String studentAge = student[1];
                String courseLine = student[2].replace("\"", "");
                stringBuilder.append(studentName).append("-").append(studentAge).append("-").append(courseLine).append("-");

                String[] studentCourses = courseLine.split(",");
                set.addAll(Arrays.asList(studentCourses));
            }
        }
        bufferedReader.close();
        courses.addAll(set);

        String[] lines = stringBuilder.toString().split("-");
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].contains(",")) {
                String[] coursesLine = lines[i].split(",");
                List<String> courseList = new ArrayList<>();
                Student student = new Student(lines[i - 2], Integer.parseInt(lines[i - 1]), courseList);
                courseList.addAll(Arrays.asList(coursesLine));
                studentsList.add(student);
            }
        }
    }


    public static void writeAndFind() throws NoSuchFieldException {
        MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
        MongoDatabase database = mongoClient.getDatabase("local");
        MongoCollection<Document> collection = database.getCollection("students");
        collection.drop();

        for (int i = 0; i < studentsList.size(); i++) {
            String thisStudentCourses = studentsList.get(i).getCourses().toString();
            thisStudentCourses = thisStudentCourses.replace("[", "")
                    .replace("]", "");
            Student s = studentsList.get(i);
            collection.insertOne(new Document().append("Name", s.getName())
                    .append("Age", s.getAge())
                    .append("Courses", thisStudentCourses));
        }


        System.out.println("Студенты старше 40 лет:");
        BasicDBObject gtQuery = new BasicDBObject();
        gtQuery.put("Age", new BasicDBObject("$gt", 40));
        FindIterable<Document> findIterable = collection.find(gtQuery);
        for (Document d : findIterable) {
            System.out.println(d);
        }
        System.out.println("Имя самого молодого студента:");
        Document first = collection.find().sort(ascending("Age")).first();
        System.out.println(first.get("Name"));

        System.out.println("Список курсов самого старого студента:");
        Document last = collection.find().sort(descending("Age")).first();
        System.out.println(first.get("Courses"));
    }
}
