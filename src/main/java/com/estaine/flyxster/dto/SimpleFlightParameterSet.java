package com.estaine.flyxster.dto;

import com.estaine.flyxster.common.FlightPeriod;

/**
 * Created by AndreyRykhalsky on 05.02.2015.
 */
public class SimpleFlightParameterSet {
    private Integer airportFromId;
    private Integer airportToId;
    private FlightPeriod flightPeriod;
    private boolean outwardFlight; //indicates whether flightPeriod restrictions are applied to arrival or departure date/time

    public SimpleFlightParameterSet(Integer airportFromId, Integer airportToId, FlightPeriod flightPeriod, boolean outwardFlight) {
        this.airportFromId = airportFromId;
        this.airportToId = airportToId;
        this.flightPeriod = flightPeriod;
        this.outwardFlight = outwardFlight;
    }

    public Integer getAirportFromId() {
        return airportFromId;
    }

    public void setAirportFromId(Integer airportFromId) {
        this.airportFromId = airportFromId;
    }

    public Integer getAirportToId() {
        return airportToId;
    }

    public void setAirportToId(Integer airportToId) {
        this.airportToId = airportToId;
    }

    public FlightPeriod getFlightPeriod() {
        return flightPeriod;
    }

    public void setFlightPeriod(FlightPeriod flightPeriod) {
        this.flightPeriod = flightPeriod;
    }

    public boolean isOutwardFlight() {
        return outwardFlight;
    }

    public void setOutwardFlight(boolean outwardFlight) {
        this.outwardFlight = outwardFlight;
    }
}
