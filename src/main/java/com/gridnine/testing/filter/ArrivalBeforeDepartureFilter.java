package com.gridnine.testing.filter;

import com.gridnine.testing.Segment;

import java.util.List;

/**
 * A flight filter implementation that checks if any segment's departure date is before the arrival date of the previous segment.
 */
public class ArrivalBeforeDepartureFilter extends AbstractFlightFilter {
    /**
     * Checks whether a flight's segments satisfy the condition that each segment's departure date is after or equal to the previous segment's arrival date.
     * @param segments List of segments associated with a flight
     * @return True if the flight's segments satisfy the condition, otherwise false
     */
    @Override
    protected boolean passesFilter(List<Segment> segments) {
        Segment prevSegment = null;
        for (Segment segment : segments) {
            if (prevSegment != null && segment.getDepartureDate().isBefore(prevSegment.getArrivalDate())) {
                return false;
            }
            prevSegment = segment;
        }
        return true;
    }
}
