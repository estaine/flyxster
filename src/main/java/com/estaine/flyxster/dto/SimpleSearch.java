package com.estaine.flyxster.dto;

/**
 * Created by AndreyRykhalsky on 04.02.2015.
 */
public class SimpleSearch extends OneWaySimpleSearch {

    protected String returnDate;
    protected Double returnDateRange;
    protected Boolean twoWayFlight = true;


    public SimpleSearch(OneWaySimpleSearch oneWaySimpleSearch, String returnDate, Double returnDateRange, Boolean twoWayFlight) {
        super(oneWaySimpleSearch);
        this.returnDate = returnDate;
        this.returnDateRange = returnDateRange;
        this.twoWayFlight = twoWayFlight;
    }

    public SimpleSearch(Integer airportFromId, Integer airportToId, String outwardDate, Double outwardDateRange, String returnDate, Double returnDateRange) {
        super(airportFromId, airportToId, outwardDate, outwardDateRange);
        this.returnDate = returnDate;
        this.returnDateRange = returnDateRange;
        this.twoWayFlight = twoWayFlight;
    }

    public SimpleSearch() {}

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public Double getReturnDateRange() {
        return returnDateRange;
    }

    public void setReturnDateRange(Double returnDateRange) {
        this.returnDateRange = returnDateRange;
    }

    public Boolean getTwoWayFlight() {
        return twoWayFlight;
    }

    public void setTwoWayFlight(Boolean twoWayFlight) {
        this.twoWayFlight = twoWayFlight;
    }
}
