package com.estaine.flyxster.service;

import com.estaine.flyxster.model.Airline;
import com.estaine.flyxster.model.Airport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by AndreyRykhalsky on 03.02.2015.
 */

public interface AirportService {

    @Transactional
    public String getAirportsBySuggestionJSON(String userInput);

    @Transactional
    public String getAirportNameCodeById(int id);

    @Transactional
    public Airport getAirportByCityName(String cityName);

    @Transactional
    public List<Airport> getAllAirports();

    @Transactional
    public Airport getAirportByCode(String code);
}
