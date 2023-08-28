package com.gridnine.testing.filter;

import com.gridnine.testing.Flight;
import com.gridnine.testing.Segment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;


public class FlightFilterTest {
    private final LocalDateTime currentTime = LocalDateTime.now();

    @Test
    public void whenDepartureBeforeCurrentTimeOneSegmentFailTest() {
        Flight departureBeforeCurrentTimeOneSegment = new Flight(List.of(
                new Segment(currentTime.minusHours(1), currentTime.plusHours(4))
        ));
        List<Flight> flights = List.of(departureBeforeCurrentTimeOneSegment);
        List<Flight> actualFlights = new DepartureBeforeCurrentTimeFilter().filter(flights);
        Assertions.assertEquals(0, actualFlights.size());
    }

    @Test
    public void whenDepartureBeforeCurrentTimeSecondSegmentFailTest() {
        Flight departureBeforeSecondSegmentArrival = new Flight(List.of(
                new Segment(currentTime.plusHours(1), currentTime.plusHours(4)),
                new Segment(currentTime.plusHours(1), currentTime.plusHours(4))
        ));
        List<Flight> flights = List.of(departureBeforeSecondSegmentArrival);
        List<Flight> actualFlights = new DepartureBeforeCurrentTimeFilter().filter(flights);
        Assertions.assertEquals(0, actualFlights.size());
    }

    @Test
    public void whenDepartureBeforeCurrentTimeTwoFlightsAndOnlyOneIsValid() {
        Flight validFlight = new Flight(List.of(
                new Segment(currentTime.plusHours(1), currentTime.plusHours(4)),
                new Segment(currentTime.plusHours(6), currentTime.plusHours(9))
        ));
        Flight departureBeforeSecondSegmentArrival = new Flight(List.of(
                new Segment(currentTime.plusHours(1), currentTime.plusHours(4)),
                new Segment(currentTime.plusHours(1), currentTime.plusHours(4))
        ));
        List<Flight> flights = List.of(departureBeforeSecondSegmentArrival, validFlight);
        List<Flight> actualFlights = new DepartureBeforeCurrentTimeFilter().filter(flights);
        Assertions.assertEquals(1, actualFlights.size());
        Assertions.assertEquals(List.of(validFlight), actualFlights);
    }

    @Test
    public void WhenArrivalBeforeDepartureOneFlightFailTest() {
        Flight arrivalBeforeDeparture = new Flight(List.of(
                new Segment(currentTime.plusHours(1), currentTime.plusHours(4)),
                new Segment(currentTime.plusHours(3), currentTime.plusHours(8))
        ));
        List<Flight> flights = List.of(arrivalBeforeDeparture);
        List<Flight> actualFlights = new ArrivalBeforeDepartureFilter().filter(flights);
        Assertions.assertEquals(0, actualFlights.size());
    }

    @Test
    public void WhenArrivalBeforeDepartureOneFlightOkTest() {
        Flight arrivalAfterDeparture = new Flight(List.of(
                new Segment(currentTime.plusHours(1), currentTime.plusHours(4)),
                new Segment(currentTime.plusHours(5), currentTime.plusHours(8))
        ));
        List<Flight> flights = List.of(arrivalAfterDeparture);
        List<Flight> actualFlights = new ArrivalBeforeDepartureFilter().filter(flights);
        Assertions.assertEquals(1, actualFlights.size());
    }

    @Test
    public void WhenArrivalBeforeDepartureTwoFlightOnlyOneOkTest() {
        Flight arrivalBeforeDeparture = new Flight(List.of(
                new Segment(currentTime.plusHours(1), currentTime.plusHours(4)),
                new Segment(currentTime.plusHours(5), currentTime.plusHours(8))
        ));
        Flight arrivalAfterDeparture = new Flight(List.of(
                new Segment(currentTime.plusHours(1), currentTime.plusHours(4)),
                new Segment(currentTime.plusHours(3), currentTime.plusHours(8))
        ));
        List<Flight> flights = List.of(arrivalBeforeDeparture, arrivalAfterDeparture);
        List<Flight> actualFlights = new ArrivalBeforeDepartureFilter().filter(flights);
        Assertions.assertEquals(1, actualFlights.size());
        Assertions.assertEquals(List.of(arrivalBeforeDeparture), actualFlights);
    }

    @Test
    public void whenTimeOneGroundMoreThen2HoursTest() {
        Flight timeOnGroundMore2Hours = new Flight(List.of(
                new Segment(currentTime.plusHours(1), currentTime.plusHours(4)),
                new Segment(currentTime.plusHours(7), currentTime.plusHours(8))
        ));
        List<Flight> flights = List.of(timeOnGroundMore2Hours);
        List<Flight> actualFlights = new TotalTimeOnGroundMoreThenTwoHoursFilter().filter(flights);
        Assertions.assertEquals(1, actualFlights.size());
    }

    @Test
    public void whenTimeOneGroundLessThen2HoursTest() {
        Flight timeOnGroundLess2Hours = new Flight(List.of(
                new Segment(currentTime.plusHours(1), currentTime.plusHours(4)),
                new Segment(currentTime.plusHours(5), currentTime.plusHours(8))
        ));
        List<Flight> flights = List.of(timeOnGroundLess2Hours);
        List<Flight> actualFlights = new TotalTimeOnGroundMoreThenTwoHoursFilter().filter(flights);
        Assertions.assertEquals(0, actualFlights.size());
    }

    @Test
    public void whenTimeOneGroundTwoFlightOneLessThen2HoursTest() {
        Flight timeOnGroundLess2Hours = new Flight(List.of(
                new Segment(currentTime.plusHours(1), currentTime.plusHours(4)),
                new Segment(currentTime.plusHours(5), currentTime.plusHours(8))
        ));
        Flight timeOnGroundMore2Hours = new Flight(List.of(
                new Segment(currentTime.plusHours(1), currentTime.plusHours(4)),
                new Segment(currentTime.plusHours(7), currentTime.plusHours(8))
        ));
        List<Flight> flights = List.of(timeOnGroundLess2Hours, timeOnGroundMore2Hours);
        List<Flight> actualFlights = new TotalTimeOnGroundMoreThenTwoHoursFilter().filter(flights);
        Assertions.assertEquals(1, actualFlights.size());
        Assertions.assertEquals(actualFlights, List.of(timeOnGroundMore2Hours));
    }
}