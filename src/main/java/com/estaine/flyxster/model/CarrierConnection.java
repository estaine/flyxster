package com.estaine.flyxster.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by AndreyRykhalsky on 02.02.2015.
 */
@Entity
@Table(name = "carriers_connections", schema = "", catalog = "flyxster")
public class CarrierConnection {
    private int id;
    private int carrierId;
    private int connectionId;
    private Set<Flight> flights;

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
    @Column(name = "carrier_id", nullable = false, insertable = true, updatable = true)
    public int getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(int carrierId) {
        this.carrierId = carrierId;
    }

    @Basic
    @Column(name = "connection_id", nullable = false, insertable = true, updatable = true)
    public int getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(int connectionId) {
        this.connectionId = connectionId;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy="carrierConnection")
    public Set<Flight> getFlights() {
        return flights;
    }

    public void setFlights(Set<Flight> flights) {
        this.flights = flights;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarrierConnection that = (CarrierConnection) o;

        if (carrierId != that.carrierId) return false;
        if (connectionId != that.connectionId) return false;
        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + carrierId;
        result = 31 * result + connectionId;
        return result;
    }
}
