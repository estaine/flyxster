package com.estaine.flyxster.service;

import com.estaine.flyxster.model.Airline;
import com.estaine.flyxster.model.Airport;
import com.estaine.flyxster.model.Connection;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by AndreyRykhalsky on 24.02.15.
 */
public interface ConnectionService {
    @Transactional
    public Connection getConnectionByAirlineAndCodes(Airline airline, String firstAirportCode, String secondAirportCode);

    @Transactional
    public void addConnection(Connection connection);
}
