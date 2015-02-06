package com.estaine.flyxster.dto;

/**
 * Created by AndreyRykhalsky on 05.02.2015.
 */
public class OneWaySimpleSearch {
    protected Integer airportFromId;
    protected Integer airportToId;
    protected String outwardDate;
    protected Double outwardDateRange;

    public OneWaySimpleSearch(Integer airportFromId, Integer airportToId, String outwardDate, Double outwardDateRange) {
        this.airportFromId = airportFromId;
        this.airportToId = airportToId;
        this.outwardDate = outwardDate;
        this.outwardDateRange = outwardDateRange;
    }

    public OneWaySimpleSearch(OneWaySimpleSearch oneWaySimpleSearch) {
        this.airportFromId = oneWaySimpleSearch.airportFromId;
        this.airportToId = oneWaySimpleSearch.airportToId;
        this.outwardDate = oneWaySimpleSearch.outwardDate;
        this.outwardDateRange = oneWaySimpleSearch.outwardDateRange;
    }

    public OneWaySimpleSearch(){}

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

    public String getOutwardDate() {
        return outwardDate;
    }

    public void setOutwardDate(String outwardDate) {
        this.outwardDate = outwardDate;
    }

    public Double getOutwardDateRange() {
        return outwardDateRange;
    }

    public void setOutwardDateRange(Double outwardDateRange) {
        this.outwardDateRange = outwardDateRange;
    }

}
