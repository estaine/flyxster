package com.estaine.flyxster.service;

import com.estaine.flyxster.dao.AirlineDAO;
import com.estaine.flyxster.model.Airline;
import com.estaine.flyxster.model.Connection;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by AndreyRykhalsky on 18.02.15.
 */
@Service
public class AirlineServiceImpl implements AirlineService {
    @Autowired
    AirlineDAO airlineDAO;

    @Override
    public Airline getAirlineByCode(String code) {
        return airlineDAO.getByProperty("code", code);
    }

    @Override
    public Set<Connection> getConnectionsByAirlineCode(String code) {
        Airline airline = getAirlineByCode(code);
        Hibernate.initialize(airline.getConnections());
        return airline.getConnections();
    }

    @Override
    public List<Airline> getAll() {
        return airlineDAO.getAll();
    }
}
