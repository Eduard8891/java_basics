import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.sql.*;


public class Main {
    static SessionFactory sessionFactory;

    public static void main(String[] args) {
        sessionFactoryMethod();
        courses();
        teachers();
        sessionFactory.close();
    }


    public static void teachers() {
        Session session = sessionFactory.openSession();
        Teachers teachers = session.get(Teachers.class, 1);
        System.out.println(teachers.getName());
    }

    public static void courses() {
        Session session = sessionFactory.openSession();
        Courses courses = session.get(Courses.class, 1);
        System.out.println(courses.getName());
    }

    public static void sessionFactoryMethod() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                        .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        sessionFactory = metadata.getSessionFactoryBuilder().build();
    }




    public static void homework1() {
        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pass = "testtest";

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT Courses.name, " +
                    "COUNT(MONTH(Subscriptions.subscription_date))/MAX(MONTH(Subscriptions.subscription_date)) FROM Courses " +
                    "JOIN Subscriptions ON Subscriptions.course_id = Courses.id GROUP BY Courses.name;");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) +" - "+ resultSet.getDouble(2));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
