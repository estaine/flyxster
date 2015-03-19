package com.estaine.flyxster.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by AndreyRykhalsky on 02.02.2015.
 */
@Entity
@Table(name = "airlines", schema = "", catalog = "flyxster")
public class Airline {
    private int id;
    private String code;
    private String name;
    private String iconLocation;
    private String classPrefix;
    private Boolean enabled;
    private Set<Connection> connections;

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
    @Column(name = "code", nullable = false, insertable = true, updatable = true)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "icon_location", nullable = false, insertable = true, updatable = true, length = 45)
    public String getIconLocation() {
        return iconLocation;
    }

    public void setIconLocation(String iconLocation) {
        this.iconLocation = iconLocation;
    }

    @Basic
    @Column(name = "class_prefix", nullable = false, insertable = true, updatable = true, length = 45)
    public String getClassPrefix() {
        return classPrefix;
    }

    public void setClassPrefix(String classPrefix) {
        this.classPrefix = classPrefix;
    }

    @Basic
    @Column(name = "enabled", nullable = false, insertable = true, updatable = true, length = 45)
    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy="airline")
    public Set<Connection> getConnections() {
        return connections;
    }

    public void setConnections(Set<Connection> connections) {
        this.connections = connections;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Airline airline = (Airline) o;

        if (id != airline.id) return false;
        if (name != null ? !name.equals(airline.name) : airline.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
