package com.estaine.flyxster.dto;

import java.sql.Timestamp;

/**
 * Created by AndreyRykhalsky on 17.02.15.
 */
public class RemoteSearchParameterSet {
    private String airportFromCode;
    private String airportToCode;
    private Timestamp flightDate;

    public RemoteSearchParameterSet(String airportFromCode, String airportToCode, Timestamp flightDate) {
        this.airportFromCode = airportFromCode;
        this.airportToCode = airportToCode;
        this.flightDate = flightDate;
    }

    public String getAirportFromCode() {
        return airportFromCode;
    }

    public void setAirportFromCode(String airportFromCode) {
        this.airportFromCode = airportFromCode;
    }

    public String getAirportToCode() {
        return airportToCode;
    }

    public void setAirportToCode(String airportToCode) {
        this.airportToCode = airportToCode;
    }

    public Timestamp getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(Timestamp flightDate) {
        this.flightDate = flightDate;
    }
}
