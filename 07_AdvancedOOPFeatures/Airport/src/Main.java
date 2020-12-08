import com.skillbox.airport.Aircraft;
import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        Date curTime = new Date(System.currentTimeMillis());
        Date addTwoHours = new Date(System.currentTimeMillis() + 7200000);


        Airport.getInstance().getTerminals()
                .stream().flatMap(t -> t.getFlights()
                .stream().filter(flight -> flight.getDate().after(curTime) &
                        flight.getDate().before(addTwoHours) & flight.getType() == Flight.Type.ARRIVAL)
                .map(Flight::toString))
                .forEach(System.out::println);

    }

}
