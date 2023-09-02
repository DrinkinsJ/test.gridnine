package com.gridnine.testing.filter;

import com.gridnine.testing.Segment;

import java.time.LocalDateTime;
import java.util.List;

/**
 * A flight filter implementation that checks if all segments' departure dates are before the current time.
 */
public class DepartureBeforeCurrentTime extends AbstractFlightFilter {

    /**
     * Checks whether all departure dates of a flight's segments are before the current time.
     * @param segments List of segments associated with a flight
     * @return True if all segments' departure dates are before the current time, otherwise false
     */
    @Override
    protected boolean passesFilter(List<Segment> segments) {
        LocalDateTime currentTime = LocalDateTime.now();
        for (Segment segment : segments) {
            if (currentTime.isBefore(segment.getDepartureDate())) {
                currentTime = segment.getArrivalDate();
            } else {
                return false;
            }
        }
        return true;
    }
}
