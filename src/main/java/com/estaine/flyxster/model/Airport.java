package com.estaine.flyxster.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by AndreyRykhalsky on 02.02.2015.
 */
@Entity
@Table(name = "airports", schema = "", catalog = "flyxster")
public class Airport {
    private int id;
    private String code;
    private String name;
    private Set<City> cities;
    private Set<Connection> connectionsFrom;
    private Set<Connection> connectionsTo;

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
    @Column(name = "code", nullable = false, insertable = true, updatable = true, length = 3)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "name", nullable = true, insertable = true, updatable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="airports_cities",
            joinColumns = {
                @JoinColumn(name="airport_id")},
            inverseJoinColumns = {
                @JoinColumn(name="city_id")}
    )
    public Set<City> getCities() {
        return cities;
    }

    public void setCities(Set<City> cities) {
        this.cities = cities;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy="airportFrom")
    public Set<Connection> getConnectionsFrom() {
        return connectionsFrom;
    }

    public void setConnectionsFrom(Set<Connection> connectionsFrom) {
        this.connectionsFrom = connectionsFrom;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy="airportTo")
    public Set<Connection> getConnectionsTo() {
        return connectionsTo;
    }

    public void setConnectionsTo(Set<Connection> connectionsTo) {
        this.connectionsTo = connectionsTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Airport airport = (Airport) o;

        if (id != airport.id) return false;
        if (code != null ? !code.equals(airport.code) : airport.code != null) return false;
        if (name != null ? !name.equals(airport.name) : airport.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
