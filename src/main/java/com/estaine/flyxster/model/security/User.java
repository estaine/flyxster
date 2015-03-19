package com.estaine.flyxster.model.security;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by AndreyRykhalsky on 11.02.15.
 */
@Entity
@Table(name = "users", schema = "", catalog = "flyxster")
public class User {
    private int id;
    private String username;
    private String password;
    private Boolean enabled;
    private String city;
    private String language;
    private Set<Role> roles;

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
    @Column(name = "username", nullable = false, insertable = true, updatable = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String login) {
        this.username = login;
    }

    @Basic
    @Column(name = "password", nullable = false, insertable = true, updatable = true)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "enabled", nullable = false, insertable = true, updatable = true)
    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Basic
    @Column(name = "city", nullable = false, insertable = true, updatable = true)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "language", nullable = false, insertable = true, updatable = true)
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="users_roles",
            joinColumns = {
                    @JoinColumn(name="user_id")},
            inverseJoinColumns = {
                    @JoinColumn(name="role_id")}
    )
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
