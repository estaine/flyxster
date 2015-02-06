package com.estaine.flyxster.common;

import com.estaine.flyxster.model.Flight;

import java.sql.Timestamp;

/**
 * Created by AndreyRykhalsky on 05.02.2015.
 */
/*
    TODO: add timezone management
 */

public class FlightPeriod {

    private Timestamp start;
    private Timestamp end;


    public FlightPeriod(Timestamp initialDate, double range) {
        start = new Timestamp(initialDate.getTime() - (int) (FlightLiterals.MILLISECONDS_PER_DAY * range));
        end = new Timestamp(initialDate.getTime() + (int) (FlightLiterals.MILLISECONDS_PER_DAY * (range + 1)));
    }

    public FlightPeriod(Flight masterLegFlight, boolean outward) {
        //creates period for a next leg based on a previous one
        if(outward) {
            start = new Timestamp(masterLegFlight.getArrivalDatetime().getTime() + FlightLiterals.MINIMUM_STOPOVER_TIME_MS);
            end = new Timestamp(masterLegFlight.getArrivalDatetime().getTime() + FlightLiterals.MAXIMUM_STOPOVER_TIME_MS);
        }
        else {
            start = new Timestamp(masterLegFlight.getDepartureDatetime().getTime() - FlightLiterals.MAXIMUM_STOPOVER_TIME_MS);
            end = new Timestamp(masterLegFlight.getDepartureDatetime().getTime() - FlightLiterals.MINIMUM_STOPOVER_TIME_MS);
        }
    }

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }
}
