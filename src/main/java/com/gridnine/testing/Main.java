package com.gridnine.testing;

import com.gridnine.testing.filter.ArrivalBeforeDeparture;
import com.gridnine.testing.filter.DepartureBeforeCurrentTime;
import com.gridnine.testing.filter.TotalTimeOnGroundMoreThenTwoHours;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        List<Flight> arrivalBeforeDeparture = new ArrivalBeforeDeparture().filter(flights);
        List<Flight> departureBeforeCurrentTime = new DepartureBeforeCurrentTime().filter(flights);
        List<Flight> totalTimeOnGround = new TotalTimeOnGroundMoreThenTwoHours().filter(flights);


        System.out.println("************************* ALL FLIGHTS ****************************************************");
        System.out.println(flights);
        System.out.println("************************* ARRIVAL BEFORE DEPARTURE ***************************************");
        System.out.println(arrivalBeforeDeparture);
        System.out.println("************************* DEPARTURE BEFORE CURRENT TIME **********************************");
        System.out.println(departureBeforeCurrentTime);
        System.out.println("************************* TOTAL TIME ON GROUND > 2 HOURS ******************************");
        System.out.println(totalTimeOnGround);

    }
}
