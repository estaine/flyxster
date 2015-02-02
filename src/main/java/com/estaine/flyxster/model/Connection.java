package com.estaine.flyxster.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by AndreyRykhalsky on 02.02.2015.
 */
@Entity
@Table(name = "connections", schema = "", catalog = "flyxster")
public class Connection {
    private int id;
    private Airport airportFrom;
    private Airport airportTo;
    private Set<Carrier> carriers;

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
    @JoinColumn(name = "airport_from_id")
    public Airport getAirportFrom() {
        return airportFrom;
    }

    public void setAirportFrom(Airport airportFromId) {
        this.airportFrom = airportFromId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airport_to_id")
    public Airport getAirportTo() {
        return airportTo;
    }

    public void setAirportTo(Airport airportToId) {
        this.airportTo = airportToId;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="carriers_connections",
            joinColumns = {
                    @JoinColumn(name="connection_id")},
            inverseJoinColumns = {
                    @JoinColumn(name="carrier_id")}
    )
    public Set<Carrier> getCarriers() {
        return carriers;
    }

    public void setCarriers(Set<Carrier> carriers) {
        this.carriers = carriers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Connection)) return false;

        Connection that = (Connection) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
