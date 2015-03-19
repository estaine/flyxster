package com.estaine.flyxster.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by AndreyRykhalsky on 02.02.2015.
 */
@Entity
@Table(name = "airports", schema = "", catalog = "flyxster")
public class Airport {
    private Integer id;
    private String code;
    private String name;
    private String timezone;
    private Set<City> cities;

    public Airport() {}

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    @Basic
    @Column(name = "timezone", nullable = false, insertable = true, updatable = true, length = 3)
    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) { this.timezone = timezone; }

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Airport airport = (Airport) o;

        if (id != airport.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        return result;
    }
}
