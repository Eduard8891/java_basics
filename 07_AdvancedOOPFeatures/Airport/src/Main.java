import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Calendar cal = new GregorianCalendar();
        Calendar cal2 = new GregorianCalendar();
        cal2.add(Calendar.HOUR, 2);
        Date curTime = cal.getTime();
        Date curTimeAfter = cal2.getTime();


        Airport.getInstance().getTerminals()
                .stream().flatMap(t -> t.getFlights()
                .stream().filter(flight -> flight.getDate().after(curTime) &
                        flight.getDate().before(curTimeAfter) & flight.getType() == Flight.Type.ARRIVAL)
                .map(Flight::toString))
                .forEach(System.out::println);

    }

}
