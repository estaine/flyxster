package com.estaine.flyxster.service;

import com.estaine.flyxster.dao.AirportDAO;
import com.estaine.flyxster.model.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by AndreyRykhalsky on 03.02.2015.
 */
@Service
public class AirportServiceImpl implements AirportService {
    @Autowired
    private AirportDAO airportDAO;

    @Override
    public String getAirportsBySuggestionJSON(String userInput) {
        List<Airport> airportList = airportDAO.getAirportsBySuggestion(userInput);
        if(airportList.isEmpty())
            return "[]";
        StringBuilder result = new StringBuilder();
        result.append("[");
        for(Airport airport : airportList) {
            result.append("{\"id\": \"");
            result.append(airport.getId());
            result.append("\", \"name\": \"");
            result.append(airport.getName());
            result.append(" (");
            result.append(airport.getCode());
            result.append(")\"},");
        }
        int lastIndex = result.lastIndexOf(",");
        result.replace(lastIndex, lastIndex + 1, "]");

        return result.toString();
    }
}
