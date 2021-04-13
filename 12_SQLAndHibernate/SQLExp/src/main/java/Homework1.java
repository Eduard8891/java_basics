import java.sql.*;

public class Homework1 {

    public static void solution() {
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
                System.out.println(resultSet.getString(1) + " - " + resultSet.getDouble(2));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public static void altersolution() {
        String URL = "jdbc:mysql://localhost:3306/skillbox";
        String USER = "root";
        String PASSWORD = "testtest";
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            //вычисляем количество месяцев с момента первой покупки и последней
            statement.execute("SELECT @count_months := " +
                    "MAX(YEAR(subscription_date) * 100 + MONTH(subscription_date)) - " +
                    "MIN(YEAR(subscription_date) * 100 + MONTH(subscription_date)) + 1 FROM purchaselist;");
            System.out.println("course_name - average_number_of_purchases_per_month");
            //среднее количество покупок в месяц по курсу = общее количество покупок курса за все время / количество прошедших месяцев за период
            ResultSet resultSet = statement.executeQuery("SELECT course_name, " +
                    "COUNT(course_name) / @count_months AS average_number_of_purchases_per_month " +
                    "FROM purchaselist GROUP BY course_name ORDER BY course_name;");
            ResultSetMetaData rsmd = resultSet.getMetaData();
            //выводим заголовок таблицы
            if (resultSet.getMetaData().getColumnCount() == 0) throw new ResultSetException("Error. No columns from request.");
            for (int i = 1, c = rsmd.getColumnCount(); i <= c; i++){
                System.out.printf("| %-38s", rsmd.getColumnName(i));
            }
            System.out.println("|\n" + String.format("%" + (rsmd.getColumnCount() * 40 + 1) + "s", "").replace(' ', '-'));
            //выводим строки таблицы
//                if (!resultSet.first()) throw new ResultSetException("Error. No rows from request.");
//                resultSet.beforeFirst();
            while(resultSet.next()){
                for (int i = 1, c = rsmd.getColumnCount(); i <= c; i++){
                    System.out.printf("| %-38s", resultSet.getString(i));
                }
                System.out.println("|");
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException | ResultSetException e) {
            e.printStackTrace();
        }
    }
}
