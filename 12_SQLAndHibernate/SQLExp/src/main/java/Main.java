import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        HashMap <String, Integer> prof = new HashMap<String, Integer>();
        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pass = "testtest";

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT Courses.name AS name, Subscription_date AS date " +
                    "FROM Courses JOIN Subscriptions ON Courses.id = Subscriptions.course_id ORDER BY Subscription_date");
            String name;
            int count;
            double maxMonth = 0;
            while (resultSet.next()) {
                name = resultSet.getString(1);
                String[] date = resultSet.getString(2).split("-");
                if (maxMonth < Double.parseDouble(date [1])) maxMonth = Double.parseDouble(date [1]);
                if (prof.containsKey(name)) {
                    count =  prof.get(name);
                    prof.replace(name,count+1);
                }
                else prof.put(name, 1);
            }
            double arifmeticMean;
            for (Map.Entry a: prof.entrySet()) {
                arifmeticMean = (int)a.getValue()/maxMonth;
                System.out.printf(a.getKey() + " - "+"%.2f", arifmeticMean);
                System.out.println("");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
