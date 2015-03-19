package com.estaine.flyxster.model;

import com.estaine.flyxster.common.TimestampConverter;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by AndreyRykhalsky on 02.02.2015.
 */
@Entity
@Table(name = "flights", schema = "", catalog = "flyxster")
public class Flight {
    private int id;
    private Airline airline;
    private String number;
    private Airport airportFrom;
    private Airport airportTo;
    private Timestamp departureDatetime;
    private Timestamp arrivalDatetime;
    private double price;
    private Timestamp added;

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airline_id")
    public Airline getAirline() {
        return airline;
    }

    @Basic
    @Column(name = "number", nullable = false, insertable = true, updatable = true)
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "airport_from_id")
    public Airport getAirportFrom() {
        return airportFrom;
    }

    public void setAirportFrom(Airport airportFrom) {
        this.airportFrom = airportFrom;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "airport_to_id")
    public Airport getAirportTo() {
        return airportTo;
    }

    public void setAirportTo(Airport airportTo) {
        this.airportTo = airportTo;
    }

    @Basic
    @Column(name = "departure_datetime", nullable = false, insertable = true, updatable = true)
    public Timestamp getDepartureDatetime() {
        return departureDatetime;
    }

    public void setDepartureDatetime(Timestamp departureDatetime) {
        this.departureDatetime = departureDatetime;
    }

    @Basic
    @Column(name = "arrival_datetime", nullable = false, insertable = true, updatable = true)
    public Timestamp getArrivalDatetime() {
        return arrivalDatetime;
    }

    public void setArrivalDatetime(Timestamp arrivalDatetime) {
        this.arrivalDatetime = arrivalDatetime;
    }

    @Basic
    @Column(name = "price", nullable = false, insertable = true, updatable = true, precision = 0)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "added", nullable = false, insertable = true, updatable = true)
    public Timestamp getAdded() {
        return added;
    }

    public void setAdded(Timestamp added) {
        this.added = added;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight flight = (Flight) o;

        if (!airline.equals(flight.airline)) return false;
        if (!arrivalDatetime.equals(flight.arrivalDatetime)) return false;
        if (!departureDatetime.equals(flight.departureDatetime)) return false;
        if (!number.equals(flight.number)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = airline.hashCode();
        result = 31 * result + number.hashCode();
        result = 31 * result + departureDatetime.hashCode();
        result = 31 * result + arrivalDatetime.hashCode();
        return result;
    }
}
