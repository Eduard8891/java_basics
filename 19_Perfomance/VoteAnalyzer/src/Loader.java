import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Loader {
    private static final SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private static final SimpleDateFormat visitDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    private static final HashMap<Integer, WorkTime> voteStationWorkTimes = new HashMap<>();
    private static final HashMap<Voter, Integer> voterCounts = new HashMap<>();
    private static final String fileName = "res/data-0.2M.xml";

    public static void main(String[] args) throws Exception {
        DOMParsingAndWriting();
    }

    private static void DOMParsingAndWriting() throws Exception {
        long start = System.currentTimeMillis();
        parseFile();
        System.out.println("Total time for DOM Parsing: " + (System.currentTimeMillis() - start));
    }


    private static void SAXParsingAndWriting() throws ParserConfigurationException, SAXException, IOException {
        long start = System.currentTimeMillis();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandler handler = new XMLHandler();
        parser.parse(new File(fileName), handler);
        handler.flush();
        System.out.println("Total time for SAX Parsing: " + (System.currentTimeMillis() - start));
    }

    private static void printResults() {
        System.out.println("Voting station work times: ");
        for (Integer votingStation : voteStationWorkTimes.keySet()) {
            WorkTime workTime = voteStationWorkTimes.get(votingStation);
            System.out.println("\t" + votingStation + " - " + workTime);
        }

        System.out.println("Duplicated voters: ");
        for (Voter voter : voterCounts.keySet()) {
            Integer count = voterCounts.get(voter);
            if (count > 1) {
                System.out.println("\t" + voter + " - " + count);
            }
        }
    }

    private static void parseFile() throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File(Loader.fileName));
        findEqualVoters(doc);
    }

    private static void findEqualVoters(Document doc) throws Exception {
        NodeList voters = doc.getElementsByTagName("voter");
        int votersCount = voters.getLength();
        int counts = 0;
        int i = 0;
        for (; ; ) {
            Node node = voters.item(i);
            if (node == null) {
                DBConnection.flush();
                break;
            }
            NamedNodeMap attributes = node.getAttributes();
            String name = attributes.getNamedItem("name").getNodeValue();
            String birthday = attributes.getNamedItem("birthDay").getNodeValue();
            DBConnection.countVoter(name, birthday);
            i++;
            counts++;

            if (counts > 4000) {
                DBConnection.executeMultiInsert();
                counts = 0;
            }
        }
    }

    private static void fixWorkTimes(Document doc) throws Exception {
        NodeList visits = doc.getElementsByTagName("visit");
        int visitCount = visits.getLength();
        for (int i = 0; i < visitCount; i++) {
            Node node = visits.item(i);
            NamedNodeMap attributes = node.getAttributes();

            Integer station = Integer.parseInt(attributes.getNamedItem("station").getNodeValue());
            Date time = visitDateFormat.parse(attributes.getNamedItem("time").getNodeValue());
            WorkTime workTime = voteStationWorkTimes.get(station);
            if (workTime == null) {
                workTime = new WorkTime();
                voteStationWorkTimes.put(station, workTime);
            }
            workTime.addVisitTime(time.getTime());
        }
    }
}