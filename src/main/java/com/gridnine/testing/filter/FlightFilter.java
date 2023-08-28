package com.gridnine.testing.filter;

import com.gridnine.testing.Flight;

import java.util.List;

/**
 * Interface for flight filtering operations.
 */
public interface FlightFilter {
    /**
     * Filters a list of flights based on specific criteria.
     * @param flights List of flights to be filtered
     * @return List of flights that satisfy the specified criteria
     */
    List<Flight> filter(List<Flight> flights);
}
