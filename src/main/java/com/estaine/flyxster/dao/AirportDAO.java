package com.estaine.flyxster.dao;

import com.estaine.flyxster.model.Airport;
import com.estaine.flyxster.model.City;

import java.util.List;

/**
 * Created by AndreyRykhalsky on 02.02.2015.
 */
public interface AirportDAO extends GenericDAO <Airport> {
    public List<Airport> getAirportsBySuggestion(String userInput);
    public Airport getAirportByCity(City city);
}
