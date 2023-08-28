package com.gridnine.testing.filter;

import com.gridnine.testing.Flight;
import com.gridnine.testing.Segment;

import java.util.List;
import java.util.stream.Collectors;

/**
 * An abstract class that implements the common functionality for flight filtering based on segments.
 * Subclasses should provide their implementation for the 'passesFilter' method.
 */
public abstract class AbstractFlightFilter implements FlightFilter {
    /**
     * Filters a list of flights based on the implemented filtering logic in 'passesFilter' method.
     * @param flights List of flights to be filtered
     * @return List of flights that pass the implemented filter
     */
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> passesFilter(flight.getSegments()))
                .collect(Collectors.toList());
    }

    /**
     * Subclasses should implement this method to provide their own filtering logic based on segments.
     * @param segments List of segments associated with a flight
     * @return True if the flight passes the filter, otherwise false
     */
    protected abstract boolean passesFilter(List<Segment> segments);
}
