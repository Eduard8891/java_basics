import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RouteCalculatorTest extends TestCase {

    List<Station> routeForCalculateDuration;
    List<Station> routeWithTwoConnection;
    List<Station> routeWithOneConnection;
    List<Station> routeViaConnectedLine;
    List<Station> routeOnTheLine;
    List<Station> connection1;
    List<Station> connection2;
    List<Station> connection3;
    StationIndex stationIndex = new StationIndex();

    @Override
    protected void setUp() throws Exception {
        routeForCalculateDuration = new ArrayList<>();
        routeWithTwoConnection = new ArrayList<>();
        routeWithOneConnection = new ArrayList<>();
        routeViaConnectedLine = new ArrayList<>();
        routeOnTheLine = new ArrayList<>();
        connection1 = new ArrayList<>();
        connection2 = new ArrayList<>();
        connection3 = new ArrayList<>();



        Line line1  = new Line(1, "first");
        Line line2  = new Line(2, "second");
        Line line3  = new Line(3, "third");

        Station left1 = new Station("left1", line1);
        Station left2 = new Station("left2", line1);
        Station left3 = new Station("left3", line1);
        Station right1 = new Station("right1", line2);
        Station right2 = new Station("right2", line2);
        Station right3 = new Station("right3", line2);
        Station horizontal1 = new Station("horizontal1", line3);
        Station horizontal2 = new Station("horizontal2", line3);
        Station horizontal3 = new Station("horizontal3", line3);
        Station horizontal4 = new Station("horizontal4", line3);
        Station horizontal5 = new Station("horizontal5", line3);
        Station horizontal6 = new Station("horizontal6", line3);
        Station horizontal7 = new Station("horizontal7", line3);

        line1.addStation(left1);
        line1.addStation(left2);
        line1.addStation(left3);
        line2.addStation(right1);
        line2.addStation(right2);
        line2.addStation(right3);
        line3.addStation(horizontal1);
        line3.addStation(horizontal2);
        line3.addStation(horizontal3);
        line3.addStation(horizontal4);
        line3.addStation(horizontal5);
        line3.addStation(horizontal6);
        line3.addStation(horizontal7);


        routeForCalculateDuration.add(left1);
        routeForCalculateDuration.add(left2);
        routeForCalculateDuration.add(horizontal3);
        routeForCalculateDuration.add(right3);

        routeOnTheLine.add(left1);
        routeOnTheLine.add(left2);
        routeOnTheLine.add(left3);

        routeViaConnectedLine.add(horizontal6);
        routeViaConnectedLine.add(horizontal7);
        routeViaConnectedLine.add(right3);
        routeViaConnectedLine.add(right2);



        routeWithOneConnection.add(right1);
        routeWithOneConnection.add(right2);
        routeWithOneConnection.add(horizontal3);
        routeWithOneConnection.add(horizontal4);

        routeWithTwoConnection.add(left1);
        routeWithTwoConnection.add(left2);
        routeWithTwoConnection.add(horizontal2);
        routeWithTwoConnection.add(horizontal3);
        routeWithTwoConnection.add(right2);
        routeWithTwoConnection.add(right3);

        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);
        stationIndex.addStation(left1);
        stationIndex.addStation(left2);
        stationIndex.addStation(left3);
        stationIndex.addStation(right1);
        stationIndex.addStation(right3);
        stationIndex.addStation(right3);
        stationIndex.addStation(horizontal1);
        stationIndex.addStation(horizontal2);
        stationIndex.addStation(horizontal3);
        stationIndex.addStation(horizontal4);
        stationIndex.addStation(horizontal5);
        stationIndex.addStation(horizontal6);
        stationIndex.addStation(horizontal7);

        connection1.add(left2);
        connection1.add(horizontal3);
        connection2.add(right2);
        connection2.add(horizontal3);
        connection3.add(horizontal7);
        connection3.add(right3);

        stationIndex.addConnection(connection1);
        stationIndex.addConnection(connection2);
        stationIndex.addConnection(connection3);

    }

    public void testCalculateDuration ()
    {
        double actual = RouteCalculator.calculateDuration(routeForCalculateDuration);
        double expected = 9.5;
        assertEquals(expected, actual);
    }

    public void testGetShortestRoute ()
    {
        Station from = stationIndex.getStation("left1", 1);
        Station to = stationIndex.getStation("left3", 1);
        RouteCalculator calculator = new RouteCalculator(stationIndex);
        List <Station> actual = calculator.getShortestRoute(from, to);
        List <Station> expected = routeOnTheLine.subList(0,3);
        assertEquals(expected, actual);
    }

    public void testGetRouteViaConnection()
    {
        Station from = stationIndex.getStation("horizontal6", 3);
        Station to = stationIndex.getStation("rightCenter", 2);
        RouteCalculator calculator = new RouteCalculator(stationIndex);
        List <Station> actual = calculator.getShortestRoute(from, to);
        List <Station> expected = routeViaConnectedLine.subList(0,4);
        assertEquals(expected, actual);
    }

    public void testGetRouteWithOneConnection()
    {
        Station from = stationIndex.getStation("right1", 2);
        Station to = stationIndex.getStation("horizontal4", 3);
        RouteCalculator calculator = new RouteCalculator(stationIndex);
        List <Station> actual = calculator.getShortestRoute(from, to);
        List <Station> expected = routeWithOneConnection.subList(0,4);
        assertEquals(expected, actual);
    }

    public void testGetRouteWithTwoConnection()
    {
        Station from = stationIndex.getStation("left1", 1);
        Station to = stationIndex.getStation("right1", 2);
        RouteCalculator calculator = new RouteCalculator(stationIndex);
        List <Station> actual = calculator.getShortestRoute(from, to);
        List <Station> expected = routeWithTwoConnection.subList(0,6);
        assertEquals(expected, actual);
    }


    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

}
