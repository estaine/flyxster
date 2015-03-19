package com.estaine.flyxster.fares;


/**
 * Created by AndreyRykhalsky on 16.02.15.
 */


import com.estaine.flyxster.common.FlightLiterals;
import com.estaine.flyxster.dto.RemoteSearchParameterSet;
import com.estaine.flyxster.fares.init.NorwegianConnections;
import com.estaine.flyxster.fares.parsing.Parser;
import com.estaine.flyxster.fares.parsing.ParserFactory;
import com.estaine.flyxster.fares.requesting.Requestor;
import com.estaine.flyxster.fares.requesting.RequestorFactory;
import com.estaine.flyxster.model.Airline;
import com.estaine.flyxster.model.Connection;
import com.estaine.flyxster.model.Flight;
import com.estaine.flyxster.service.AirlineService;
import com.estaine.flyxster.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;
import java.sql.Timestamp;

@EnableScheduling
@Component("fareUpdater")
public class FareUpdater {
    @Autowired
    private FlightService flightService;

    @Autowired
    AirlineService airlineService;

    @Autowired
    ParserFactory parserFactory;

    @Autowired
    RequestorFactory requestorFactory;

    @Autowired
    NorwegianConnections norwegianConnections;

    final int daysToScan = 20;

    @Scheduled(cron="30 58 10 * * ?", zone="Etc/UTC")
    public void updateFares(){

        /*
            TODO: Make a factory for connection loaders
         */
        //should be run only once to load connections in the DB
       //norwegianConnections.loadConnections();


        List<Airline> airlines = airlineService.getAll();

        for(Airline airline : airlines) {

            if(!airline.getEnabled())
                continue;
            Set<Connection> connections = airlineService.getConnectionsByAirlineCode(airline.getCode());
            Parser parser = parserFactory.getParser(airline.getClassPrefix());
            Requestor requestor = requestorFactory.getRequestor(airline.getClassPrefix());

            long scanStart = (new Date()).getTime(); // start point for flight scanning = now

            for(Connection connection : connections)
                for(long millisCounter = scanStart; millisCounter < scanStart + FlightLiterals.MILLISECONDS_PER_DAY * daysToScan; millisCounter +=  FlightLiterals.MILLISECONDS_PER_DAY)
                {
                    //Direct direction
                    RemoteSearchParameterSet requestParams =
                            new RemoteSearchParameterSet(connection.getFirstAirport().getCode(),
                                    connection.getSecondAirport().getCode(),
                                    new Timestamp(millisCounter));

                    doUpdate(requestParams, parser, requestor);

                    //Return direction
                    requestParams =
                            new RemoteSearchParameterSet(connection.getSecondAirport().getCode(),
                                    connection.getFirstAirport().getCode(),
                                    new Timestamp(millisCounter));

                    doUpdate(requestParams, parser, requestor);
                }
        }
    }

    private void doUpdate(RemoteSearchParameterSet requestParams, Parser parser, Requestor requestor) {
        String response = requestor.request(requestParams);
        List<Flight> flights = parser.parse(response);
        if((flights != null) && (!flights.isEmpty()))
            flightService.bulkUpdateFlights(flights);
    }

}

