package com.estaine.flyxster.service;

import com.estaine.flyxster.dao.AirportDAO;
import com.estaine.flyxster.dao.ConnectionDAO;
import com.estaine.flyxster.model.Airline;
import com.estaine.flyxster.model.Airport;
import com.estaine.flyxster.model.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AndreyRykhalsky on 24.02.15.
 */
@Service
public class ConnectionServiceImpl implements ConnectionService {

    @Autowired
    ConnectionDAO connectionDAO;

    @Autowired
    AirportDAO airportDAO;

    @Override
    public Connection getConnectionByAirlineAndCodes(Airline airline, String firstAirportCode, String secondAirportCode) {



        Airport firstAirport = airportDAO.getByProperty("code", firstAirportCode);
        Airport secondAirport = airportDAO.getByProperty("code", secondAirportCode);
        if((firstAirport == null) || (secondAirport == null))
            return null;

        Map<String, Object> propertiesDirect = new HashMap<>();
        propertiesDirect.put("airline", airline);
        propertiesDirect.put("firstAirport", firstAirport);
        propertiesDirect.put("secondAirport", secondAirport);


        Connection connection = connectionDAO.getByProperties(propertiesDirect);
        if(connection != null)
            return connection;

        Map<String, Object> propertiesInverted = new HashMap<>();
        propertiesDirect.put("airline", airline);
        propertiesDirect.put("firstAirport", secondAirport);
        propertiesDirect.put("secondAirport", firstAirport);

        return connectionDAO.getByProperties(propertiesInverted);
    }

    @Override
    public void addConnection(Connection connection) {
        connectionDAO.create(connection);
    }
}
