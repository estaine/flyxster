package com.estaine.flyxster.service;

import com.estaine.flyxster.model.Airline;
import com.estaine.flyxster.model.Airport;
import com.estaine.flyxster.model.Connection;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Created by AndreyRykhalsky on 18.02.15.
 */
public interface AirlineService {

    @Transactional
    public Airline getAirlineByCode(String code);

    @Transactional
    public Set<Connection> getConnectionsByAirlineCode(String code);

    @Transactional
    public List<Airline> getAll();

}
