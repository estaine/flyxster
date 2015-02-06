package com.estaine.flyxster.model;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airport_from_id")
    public Airport getAirportFrom() {
        return airportFrom;
    }

    public void setAirportFrom(Airport airportFrom) {
        this.airportFrom = airportFrom;
    }

    @ManyToOne(fetch = FetchType.LAZY)
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

}
