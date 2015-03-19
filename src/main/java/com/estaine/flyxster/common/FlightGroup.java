package com.estaine.flyxster.common;

import com.estaine.flyxster.model.Airline;
import com.estaine.flyxster.model.Airport;
import com.estaine.flyxster.model.Flight;

import java.sql.Timestamp;
import java.util.*;

/**
 * Created by AndreyRykhalsky on 05.02.2015.
 */
public class FlightGroup extends ArrayList<Flight> implements Comparable<FlightGroup> {

    private int outwardSize = 0;

    public FlightGroup() {}

    @Override
    public boolean add(Flight flight) {
        return super.add(flight);
    }

    public Integer getOutwardStops() {
        return (isEmpty()) ? null : outwardSize - 1;
    }

    public Integer getReturnStops() {
        return (isEmpty() || size() <= outwardSize) ? null : size() - outwardSize - 1;
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for(Flight flight : this)
            totalPrice += flight.getPrice();
        return totalPrice;
    }

    public Set<Airline> getAirlines() {
        Set<Airline> airlines = new HashSet<Airline>();
        for(Flight flight : this)
            airlines.add(flight.getAirline());
        return airlines;
    }

    public Airport getDepartureAirport() {
        return (isEmpty()) ? null : get(0).getAirportFrom();
    }

    public Timestamp getTripStartTime() {
        return (isEmpty()) ? null : get(0).getDepartureDatetime();
    }

    public Timestamp getTripEndTime() {

        if(isEmpty()) return null;
        Flight lastFlight = get(size() - 1);
        return lastFlight.getArrivalDatetime();
    }

    public TimeZone getTripStartTimeZone() {
        return (isEmpty()) ? null : TimeZone.getTimeZone(get(0).getAirportFrom().getTimezone());
    }

    public TimeZone getTripEndTimeZone() {
        if(isEmpty()) return null;
        Flight lastFlight = get(size() - 1);
        return TimeZone.getTimeZone(lastFlight.getAirportTo().getTimezone());
    }

    public Airport getDestinationAirport() {
        return (isEmpty() || outwardSize > size()) ? null : get(outwardSize - 1).getAirportTo();
    }

    public int getOutwardSize() {
        return outwardSize;
    }

    public void setOutwardSize(int outwardSize) {
        this.outwardSize = outwardSize;
    }

    //ensure that return flights starts no earlier than last outward leg arrival + min. stopover time
    public static boolean checkGroupsCompatibility(FlightGroup outwardFlight, FlightGroup returnFlight) {
        return ((outwardFlight.getTripEndTime().getTime() + FlightLiterals.MINIMUM_STOPOVER_TIME_MS) < returnFlight.getTripStartTime().getTime());
    }

    @Override
    public int compareTo(FlightGroup otherFlightGroup) {
        Double thisFlightGroupPrice = new Double(this.getTotalPrice());
        Double otherFlightGroupPrice = new Double(otherFlightGroup.getTotalPrice());
        return thisFlightGroupPrice.compareTo(otherFlightGroupPrice);
    }


}
