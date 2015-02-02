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
    private CarrierConnection carrierConnection;
    private Timestamp departureDatetime;
    private int flightDuration;
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

    @Basic
    @Column(name = "departure_datetime", nullable = false, insertable = true, updatable = true)
    public Timestamp getDepartureDatetime() {
        return departureDatetime;
    }

    public void setDepartureDatetime(Timestamp departureDatetime) {
        this.departureDatetime = departureDatetime;
    }

    @Basic
    @Column(name = "flight_duration", nullable = false, insertable = true, updatable = true)
    public int getFlightDuration() {
        return flightDuration;
    }

    public void setFlightDuration(int flightDuration) {
        this.flightDuration = flightDuration;
    }

    @Basic
    @Column(name = "price", nullable = false, insertable = true, updatable = true, precision = 0)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flight)) return false;

        Flight flight = (Flight) o;

        if (flightDuration != flight.flightDuration) return false;
        if (id != flight.id) return false;
        if (Double.compare(flight.price, price) != 0) return false;
        if (!departureDatetime.equals(flight.departureDatetime)) return false;

        return true;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carrier_connection_id")
    public CarrierConnection getCarrierConnection() {
        return carrierConnection;
    }

    public void setCarrierConnection(CarrierConnection carrierConnection) {
        this.carrierConnection = carrierConnection;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + departureDatetime.hashCode();
        result = 31 * result + flightDuration;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
