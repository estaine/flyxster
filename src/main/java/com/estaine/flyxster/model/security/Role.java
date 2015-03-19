package com.estaine.flyxster.model.security;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by AndreyRykhalsky on 11.02.15.
 */
@Entity
@Table(name = "roles", schema = "", catalog = "flyxster")
public class Role {
    private int id;
    private String name;
    private Set<User> users;

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
    @Column(name = "name", nullable = false, insertable = true, updatable = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
