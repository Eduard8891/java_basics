import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class RouteCalculatorTest extends TestCase {

    List<Station> route;
    List<Station> route2;
    List<Station> connection1;
    List<Station> connection2;
    StationIndex stationIndex = new StationIndex();

    @Override
    protected void setUp() throws Exception {
        route = new ArrayList<>();

        Line lineFirst  = new Line(1, "first");
        Line lineSecond  = new Line(2, "second");
        Line lineThird  = new Line(3, "third");

        Station leftDown = new Station("leftDown", lineFirst);
        Station leftCenter = new Station("leftCenter", lineFirst);
        Station leftUp = new Station("leftUp", lineFirst);
        Station rightDown = new Station("rightDown", lineSecond);
        Station rightCenter = new Station("rightCenter", lineSecond);
        Station rightUp = new Station("rightUp", lineSecond);
        Station horizontalLeft = new Station("horizontalLeft", lineThird);
        Station horizontalCenterLeft = new Station("horizontalCenterLeft", lineThird);
        Station horizontalCenterRight = new Station("horizontalCenterRight", lineThird);
        Station horizontalRight = new Station("horizontalRight", lineThird);

        lineFirst.addStation(leftDown);
        lineFirst.addStation(leftCenter);
        lineFirst.addStation(leftUp);
        lineSecond.addStation(rightDown);
        lineSecond.addStation(rightCenter);
        lineSecond.addStation(rightUp);
        lineThird.addStation(horizontalLeft);
        lineThird.addStation(horizontalCenterLeft);
        lineThird.addStation(horizontalCenterRight);
        lineThird.addStation(horizontalRight);


        route.add(leftDown);
        route.add(leftCenter);
        route.add(horizontalCenterRight);
        route.add(rightUp);

        route2.add(leftDown);
        route2.add(leftCenter);
        route2.add(leftUp);
        route2.add(horizontalLeft);
        route2.add(horizontalCenterLeft);
        route2.add(horizontalCenterRight);
        route2.add(horizontalRight);
        route2.add(rightDown);
        route2.add(rightCenter);
        route2.add(rightUp);

        connection1.add(1, leftCenter);
        connection1.add(3, horizontalCenterRight);
        connection2.add(2, rightCenter);
        connection2.add(3, horizontalCenterRight);

        stationIndex.addLine(lineFirst);
        stationIndex.addLine(lineSecond);
        stationIndex.addLine(lineThird);
        stationIndex.addStation(leftDown);
        stationIndex.addStation(leftCenter);
        stationIndex.addStation(leftUp);
        stationIndex.addStation(rightDown);
        stationIndex.addStation(rightCenter);
        stationIndex.addStation(rightUp);
        stationIndex.addStation(horizontalLeft);
        stationIndex.addStation(horizontalCenterLeft);
        stationIndex.addStation(horizontalCenterRight);
        stationIndex.addStation(horizontalRight);
        stationIndex.addConnection(connection1);
        stationIndex.addConnection(connection2);

    }

    public void testCalculateDuration ()
    {
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 9.5;
        assertEquals(expected, actual);
    }

    public void testGetShortestRoute ()
    {

        Station from = stationIndex.getStation("leftDown", 1);
        Station to = stationIndex.getStation("rightUp", 2);
        RouteCalculator calculator = new RouteCalculator(stationIndex);
        List <Station> actual = calculator.getShortestRoute(from, to);
        List <Station> expected = route.subList(0,9);
        assertEquals(expected, actual);
    }


    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

}
