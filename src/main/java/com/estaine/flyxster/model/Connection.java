package com.estaine.flyxster.model;

import javax.persistence.*;

/**
 * Created by AndreyRykhalsky on 19.02.15.
 */
@Entity
@Table(name = "connections", schema = "", catalog = "flyxster")
public class Connection {
    private int id;
    private Airline airline;
    private Airport firstAirport;
    private Airport secondAirport;

    public Connection() {}


    public Connection(Airline airline, Airport firstAirport, Airport secondAirport) {
        this.airline = airline;
        this.firstAirport = firstAirport;
        this.secondAirport = secondAirport;
    }

    @Id
    @Column(name = "id")
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

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "first_airport_id")
    public Airport getFirstAirport() {
        return firstAirport;
    }

    public void setFirstAirport(Airport firstAirport) {
        this.firstAirport = firstAirport;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "second_airport_id")
    public Airport getSecondAirport() {
        return secondAirport;
    }

    public void setSecondAirport(Airport secondAirport) {
        this.secondAirport = secondAirport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Connection that = (Connection) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
