import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class XMLHandler extends DefaultHandler {
    private int counts = 0;
    private static final SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");

    private StringBuilder insertQuery;
    private final Connection connection;

    public XMLHandler() {
        insertQuery = new StringBuilder();
        connection = DBConnection.getConnection();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (counts < 5000) {
            try {
                if (qName.equals("voter")) {
                    Date birthday = birthDayFormat.parse(attributes.getValue("birthDay"));
                    String name = attributes.getValue("name");
                    String dateForm = birthDayFormat.format(birthday).replace('.', '-');
                    if (name != null && birthday != null) {
                        boolean isStart = insertQuery.length() == 0;
                        insertQuery.append((isStart ? "" : ",") +
                                "('" + name + "', '" + dateForm + "', 1)");
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            counts++;
        } else {

            String sql = "INSERT INTO voter_count(name, birthDate, `count`) " +
                    "VALUES" + insertQuery.toString() +
                    "ON DUPLICATE KEY UPDATE `count`=`count` + 1";
            try {
                connection.createStatement().execute(sql);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            insertQuery = new StringBuilder();
            counts = 0;
        }
    }

    public void flush() {
        String sql = "INSERT INTO voter_count(name, birthDate, `count`) " +
                "VALUES" + insertQuery.toString() +
                "ON DUPLICATE KEY UPDATE `count`=`count` + 1";
        try {
            connection.createStatement().execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
