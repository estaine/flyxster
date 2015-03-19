package com.estaine.flyxster.model;

import javax.persistence.*;

/**
 * Created by AndreyRykhalsky on 10.03.15.
 */
@Entity
@Table(name = "search_lines", schema = "", catalog = "flyxster")
public class SearchLine {
    private int id;
    private int searchId;
    private int flightGroupIndex;
    private int flightIndex;
    private Flight flight;

    public SearchLine() {
    }

    public SearchLine(int searchId, int flightGroupIndex, int flightIndex, Flight flight) {
        this.searchId = searchId;
        this.flightGroupIndex = flightGroupIndex;
        this.flightIndex = flightIndex;
        this.flight = flight;
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "search_id")
    public int getSearchId() {
        return searchId;
    }

    public void setSearchId(int seachId) {
        this.searchId = seachId;
    }

    @Basic
    @Column(name = "flight_index")
    public int getFlightIndex() {
        return flightIndex;
    }

    public void setFlightIndex(int flightIndex) {
        this.flightIndex = flightIndex;
    }

    @Basic
    @Column(name = "flight_group_index")
    public int getFlightGroupIndex() {
        return flightGroupIndex;
    }

    public void setFlightGroupIndex(int flightGroupIndex) {
        this.flightGroupIndex = flightGroupIndex;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id")
    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchLine that = (SearchLine) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
