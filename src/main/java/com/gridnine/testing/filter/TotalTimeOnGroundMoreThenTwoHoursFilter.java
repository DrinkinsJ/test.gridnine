package com.gridnine.testing.filter;

import com.gridnine.testing.Segment;

import java.time.Duration;
import java.util.List;

/**
 * A flight filter implementation that checks if the total time on the ground (the interval between the arrival of one segment and the departure of the next) exceeds two hours.
 */
public class TotalTimeOnGroundMoreThenTwoHoursFilter extends AbstractFlightFilter {

    private static final Duration MIN_TOTAL_GROUND_TIME = Duration.ofHours(2);

    /**
     * Checks whether a flight's segments have a total time on the ground that is less than or equal to two hours.
     * @param segments List of segments associated with a flight
     * @return True if the total time on the ground is less than or equal to two hours, otherwise false
     */
    @Override
    protected boolean passesFilter(List<Segment> segments) {
        Duration totalGroundTime = Duration.ZERO;
        Segment previousSegment = null;

        for (Segment currentSegment : segments) {
            if (previousSegment != null) {
                Duration groundTime = Duration.between(previousSegment.getArrivalDate(), currentSegment.getDepartureDate());
                totalGroundTime = totalGroundTime.plus(groundTime);
            }
            previousSegment = currentSegment;
        }

        return totalGroundTime.compareTo(MIN_TOTAL_GROUND_TIME) > 0;
    }
}
