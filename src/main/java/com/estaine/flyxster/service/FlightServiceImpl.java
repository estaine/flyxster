package com.estaine.flyxster.service;

import com.estaine.flyxster.common.FlightGroup;
import com.estaine.flyxster.common.FlightPeriod;
import com.estaine.flyxster.common.TimestampConverter;
import com.estaine.flyxster.dao.AirportDAO;
import com.estaine.flyxster.dao.CityDAO;
import com.estaine.flyxster.dao.FlightDAO;
import com.estaine.flyxster.dto.DirectedOneWaySimpleSearch;
import com.estaine.flyxster.dto.SimpleFlightParameterSet;
import com.estaine.flyxster.dto.SimpleSearch;
import com.estaine.flyxster.model.Flight;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

/**
 * Created by AndreyRykhalsky on 03.02.2015.
 */
@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightDAO flightDAO;

    @Autowired
    private CityDAO cityDAO;

    @Autowired
    private AirportDAO airportDAO;


    /*
        TODO: Refactor flight processing to make the implementation independent of number of legs and direction
     */



    private Set<FlightGroup> getOneWayDirectFlights(DirectedOneWaySimpleSearch directedOneWaySimpleSearch, FlightPeriod flightPeriod) {

        SortedSet<FlightGroup> result = new TreeSet<FlightGroup>();

        SimpleFlightParameterSet parameterSet =
                new SimpleFlightParameterSet(
                        directedOneWaySimpleSearch.getAirportFromId(),
                        directedOneWaySimpleSearch.getAirportToId(),
                        flightPeriod,
                        directedOneWaySimpleSearch.isOutward()
                );

         List<Flight> directFlights = flightDAO.getFlightsBySimpleParameterSet(parameterSet);
        for(Flight directFlight : directFlights) {
            FlightGroup resultLine = new FlightGroup();
            resultLine.add(directFlight);
            result.add(resultLine);
        }
        return result;

    }

    private Set<FlightGroup> getOneWayDirectFlights(DirectedOneWaySimpleSearch directedOneWaySimpleSearch) {
        Timestamp timestamp = TimestampConverter.toTimestamp(directedOneWaySimpleSearch.getOutwardDate());
        FlightPeriod flightPeriod = new FlightPeriod(timestamp, directedOneWaySimpleSearch.getOutwardDateRange());
        return getOneWayDirectFlights(directedOneWaySimpleSearch, flightPeriod);
    }

    private Set<FlightGroup> getOneWayStopoverFlights(DirectedOneWaySimpleSearch directedOneWaySimpleSearch) {

        SortedSet<FlightGroup> result = new TreeSet<FlightGroup>();

        //master leg means 1st for outward and 2nd for return flight
        //date and time of start/end of a trip are date and time of correspondent master legs departure/arrival
        DirectedOneWaySimpleSearch masterLegSimpleSearch = new DirectedOneWaySimpleSearch(directedOneWaySimpleSearch);
        if(directedOneWaySimpleSearch.isOutward())
            masterLegSimpleSearch.setAirportToId(null);
        else
            masterLegSimpleSearch.setAirportFromId(null);

        Set<FlightGroup> masterLegFlights = getOneWayDirectFlights(masterLegSimpleSearch);

        for(FlightGroup masterLegFlightGroup : masterLegFlights) {
            Flight masterLegFlight = masterLegFlightGroup.get(0);

            DirectedOneWaySimpleSearch slaveLegSimpleSearch = new DirectedOneWaySimpleSearch(directedOneWaySimpleSearch);

            if(directedOneWaySimpleSearch.isOutward())
                slaveLegSimpleSearch.setAirportFromId(masterLegFlight.getAirportTo().getId());
            else
                slaveLegSimpleSearch.setAirportToId(masterLegFlight.getAirportFrom().getId());

            Set<FlightGroup> slaveLegFlights = getOneWayDirectFlights(slaveLegSimpleSearch, new FlightPeriod(masterLegFlight, directedOneWaySimpleSearch.isOutward()));

            for(FlightGroup slaveLegFlightGroup : slaveLegFlights) {
                Flight slaveLegFlight = slaveLegFlightGroup.get(0);
                FlightGroup resultLine = new FlightGroup();

                if(directedOneWaySimpleSearch.isOutward()) {
                    resultLine.add(masterLegFlight);
                    resultLine.add(slaveLegFlight);
                }

                else {
                    resultLine.add(slaveLegFlight);
                    resultLine.add(masterLegFlight);
                }
                result.add(resultLine);
            }
        }
        return result;
    }

    private Set<FlightGroup> getOneWayFlights(DirectedOneWaySimpleSearch directedOneWaySimpleSearch) {
        SortedSet<FlightGroup> result = new TreeSet<FlightGroup>();
        result.addAll(getOneWayDirectFlights(directedOneWaySimpleSearch));
        result.addAll(getOneWayStopoverFlights(directedOneWaySimpleSearch));
        return result;
    }

    public Set<FlightGroup> getFlights(SimpleSearch simpleSearch) {

        DirectedOneWaySimpleSearch outwardSearch = new DirectedOneWaySimpleSearch(simpleSearch, DirectedOneWaySimpleSearch.OUTWARD);
        Set<FlightGroup> outwardFlightGroups = getOneWayFlights(outwardSearch);
        for(FlightGroup outwardFlightGroup : outwardFlightGroups)
            outwardFlightGroup.setOutwardSize(outwardFlightGroup.size());

        DirectedOneWaySimpleSearch returnSearch = new DirectedOneWaySimpleSearch(
                simpleSearch.getAirportToId(),
                simpleSearch.getAirportFromId(),
                simpleSearch.getReturnDate(),
                simpleSearch.getReturnDateRange(),
                DirectedOneWaySimpleSearch.RETURN
        );

        Set<FlightGroup> result = new TreeSet<FlightGroup>();

        if(simpleSearch.getReturnDate() != "") {
            Set<FlightGroup> returnFlightGroups = getOneWayFlights(returnSearch);

            //Form result as cartesian product of all outward and return flights
            for (FlightGroup outwardFlightGroup : outwardFlightGroups)
                for (FlightGroup returnFlightGroup : returnFlightGroups) {
                    FlightGroup twoWayFlightGroup = new FlightGroup();

                    if (FlightGroup.checkGroupsCompatibility(outwardFlightGroup, returnFlightGroup)) {
                        twoWayFlightGroup.addAll(outwardFlightGroup);
                        twoWayFlightGroup.addAll(returnFlightGroup);
                        twoWayFlightGroup.setOutwardSize(outwardFlightGroup.getOutwardSize());
                        result.add(twoWayFlightGroup);
                    }
                }
        }
        else result = outwardFlightGroups;


        /*
            TODO: Setup OpenSessionInViewFilter instead of explicit initialization
         */
            for (FlightGroup flightGroup : result)
                for (Flight flight : flightGroup) {
                    Hibernate.initialize(flight.getAirline());
                    Hibernate.initialize(flight.getAirportFrom());
                    Hibernate.initialize(flight.getAirportTo());
                }

        return result;
    }
}
