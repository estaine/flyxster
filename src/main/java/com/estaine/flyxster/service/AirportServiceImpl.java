package com.estaine.flyxster.service;

import com.estaine.flyxster.dao.AirportDAO;
import com.estaine.flyxster.dao.CityDAO;
import com.estaine.flyxster.model.Airport;
import com.estaine.flyxster.model.City;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AndreyRykhalsky on 03.02.2015.
 */
@Service
public class AirportServiceImpl implements AirportService {
    @Autowired
    private AirportDAO airportDAO;

    @Autowired
    private CityDAO cityDAO;

    private class AirportJSON {
        private Integer id;
        private String name;


        private AirportJSON(Integer id, String airportName, String airportCode) {
            this.id = id;
            this.name = airportName + " (" + airportCode + ")";
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
    }


    @Override
    public String getAirportsBySuggestionJSON(String userInput) {
        List<Airport> airportList = airportDAO.getAirportsBySuggestion(userInput);

        List<AirportJSON> airportJSONList = new ArrayList<>();

        for(Airport airport : airportList)
            airportJSONList.add(new AirportJSON(airport.getId(), airport.getName(), airport.getCode()));

        return (new Gson()).toJson(airportJSONList);
    }

    @Override
    public String getAirportNameCodeById(int id) {
        Airport airport = airportDAO.get(id);
        return airport.getName() + " (" + airport.getCode() + ")";
    }

    @Override
    public Airport getAirportByCityName(String cityName) {
        City city = cityDAO.getByProperty("name", cityName);
        return airportDAO.getAirportByCity(city);
    }

    @Override
    public List<Airport> getAllAirports() {
        return airportDAO.getAll();
    }

    @Override
    public Airport getAirportByCode(String code) {
        return airportDAO.getByProperty("code", code);
    }
}
