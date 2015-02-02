package com.estaine.flyxster.model;

import javax.persistence.*;

/**
 * Created by AndreyRykhalsky on 02.02.2015.
 */
@Entity
@Table(name = "airports_cities", schema = "", catalog = "flyxster")
public class AirportCity {
    private int id;
    private int airportId;
    private int cityId;

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
    @Column(name = "airport_id", nullable = false, insertable = true, updatable = true)
    public int getAirportId() {
        return airportId;
    }

    public void setAirportId(int airportId) {
        this.airportId = airportId;
    }

    @Basic
    @Column(name = "city_id", nullable = false, insertable = true, updatable = true)
    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AirportCity that = (AirportCity) o;

        if (airportId != that.airportId) return false;
        if (cityId != that.cityId) return false;
        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + airportId;
        result = 31 * result + cityId;
        return result;
    }
}
