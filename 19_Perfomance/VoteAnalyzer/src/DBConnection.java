import java.sql.*;

public class DBConnection {
    private static Connection connection;

    private static String dbName = "learn";
    private static String dbUser = "root";
    private static String dbPass = "12345678";

    private static StringBuilder insertQuery = new StringBuilder();

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/" + dbName + "?useSSL=false", dbUser, dbPass);
                connection.createStatement().execute("DROP TABLE IF EXISTS voter_count");
                connection.createStatement().execute("CREATE TABLE voter_count(" +
                        "id INT NOT NULL AUTO_INCREMENT, " +
                        "name TINYTEXT NOT NULL, " +
                        "birthDate DATE NOT NULL, " +
                        "`count` INT NOT NULL, " +
                        "PRIMARY KEY(id), " +
                        "KEY (name(50)))");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void executeMultiInsert() throws SQLException {
        String sql = "INSERT INTO voter_count(name, birthDate, `count`) " +
                "VALUES" + insertQuery.toString() +
                "ON DUPLICATE KEY UPDATE `count`=`count` + 1";
        DBConnection.getConnection().createStatement().execute(sql);
        insertQuery = new StringBuilder();
    }

    public static void countVoter(String name, String birthDay) throws SQLException {
        birthDay = birthDay.replace('.', '-');
        boolean isStart = insertQuery.length() == 0;
        insertQuery.append((isStart ? "" : ",") +
                "('" + name + "', '" + birthDay + "', 1)");
    }

    public static void flush() {
        String sql = "INSERT INTO voter_count(name, birthDate, `count`) " +
                "VALUES" + insertQuery.toString() +
                "ON DUPLICATE KEY UPDATE `count`=`count` + 1";
        try {
            DBConnection.getConnection().createStatement().execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void printVoterCounts() throws SQLException {
        String sql = "SELECT name, birthDate, `count` FROM voter_count WHERE `count` > 1";
        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
        while (rs.next()) {
            System.out.println("\t" + rs.getString("name") + " (" +
                    rs.getString("birthDate") + ") - " + rs.getInt("count"));
        }
        rs.close();
    }
}
