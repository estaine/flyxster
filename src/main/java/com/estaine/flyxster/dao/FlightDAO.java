package com.estaine.flyxster.dao;

import com.estaine.flyxster.dto.SimpleFlightParameterSet;
import com.estaine.flyxster.model.Flight;

import java.util.List;

/**
 * Created by AndreyRykhalsky on 02.02.2015.
 */
public interface FlightDAO extends GenericDAO<Flight> {
    public List<Flight> getFlightsBySimpleParameterSet(SimpleFlightParameterSet simpleFlightParameterSet);

}
