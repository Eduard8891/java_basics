import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
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
