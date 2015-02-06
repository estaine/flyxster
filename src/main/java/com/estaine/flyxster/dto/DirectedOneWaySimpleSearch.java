package com.estaine.flyxster.dto;

/**
 * Created by AndreyRykhalsky on 06.02.2015.
 */
public class DirectedOneWaySimpleSearch extends OneWaySimpleSearch {

    public static final boolean OUTWARD = true;
    public static final boolean RETURN = false;

    protected boolean outward; //shows whether a flight is outward or return; this has date range impact

    public DirectedOneWaySimpleSearch(Integer airportFromId, Integer airportToId, String outwardDate, Double outwardDateRange, boolean outward) {
        super(airportFromId, airportToId, outwardDate, outwardDateRange);
        this.outward = outward;
    }

    public DirectedOneWaySimpleSearch(OneWaySimpleSearch oneWaySimpleSearch, boolean outward) {
        super(oneWaySimpleSearch);
        this.outward = outward;
    }

    public DirectedOneWaySimpleSearch(boolean outward) {
        this.outward = outward;
    }

    public DirectedOneWaySimpleSearch(DirectedOneWaySimpleSearch directedOneWaySimpleSearch) {
        super(directedOneWaySimpleSearch);
        this.outward = directedOneWaySimpleSearch.isOutward();
    }

    public boolean isOutward() {
        return outward;
    }

    public void setOutward(boolean outward) {
        this.outward = outward;
    }
}
