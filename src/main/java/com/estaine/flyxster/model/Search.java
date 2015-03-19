package com.estaine.flyxster.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by AndreyRykhalsky on 10.03.15.
 */
@Entity
@Table(name = "searches", schema = "", catalog = "flyxster")
public class Search {
    private int id;
    private Timestamp created;

    @Id
    @GeneratedValue
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "created")
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Search search = (Search) o;

        if (id != search.id) return false;
        if (created != null ? !created.equals(search.created) : search.created != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (created != null ? created.hashCode() : 0);
        return result;
    }
}
