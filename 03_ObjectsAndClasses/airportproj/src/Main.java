import com.skillbox.airport.Aircraft;
import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.util.ArrayList;
import java.util.List;

import static com.skillbox.airport.Airport.*;

public class Main {
    public static void main(String[] args) {
       System.out.println(Airport.getInstance().getAllAircrafts().size());
    }
}
