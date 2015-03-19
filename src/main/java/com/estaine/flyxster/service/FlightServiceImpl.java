package com.estaine.flyxster.service;

import com.estaine.flyxster.common.FlightGroup;
import com.estaine.flyxster.common.FlightPeriod;
import com.estaine.flyxster.common.TimestampConverter;
import com.estaine.flyxster.dao.*;
import com.estaine.flyxster.dto.DirectedOneWaySimpleSearch;
import com.estaine.flyxster.dto.SimpleFlightParameterSet;
import com.estaine.flyxster.dto.SimpleSearch;
import com.estaine.flyxster.model.Flight;
import com.estaine.flyxster.model.Search;
import com.estaine.flyxster.model.SearchLine;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Autowired
    private SearchDAO searchDAO;

    @Autowired
    private SearchLineDAO searchLineDAO;


    /*
        TODO: Refactor flight processing to make the implementation independent of number of legs and direction
     */

  /* private void removeOutdatedFlights(List<Flight> flights) {
        for(Flight flight : flights) {
            for(Flight comparedFlight : flights) {
                if((flight.equals(comparedFlight)) && (flight.getId() != comparedFlight.getId())) {
                    if(flight.getAdded().getTime() > comparedFlight.getAdded().getTime())
                        flights.remove()
                }

            }
        }
    }*/

    private List<FlightGroup> getOneWayDirectFlights(DirectedOneWaySimpleSearch directedOneWaySimpleSearch, FlightPeriod flightPeriod) {

        List<FlightGroup> result = new ArrayList<>();

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

    private List<FlightGroup> getOneWayDirectFlights(DirectedOneWaySimpleSearch directedOneWaySimpleSearch) {
        Timestamp timestamp = TimestampConverter.toTimestamp(directedOneWaySimpleSearch.getOutwardDate());
        FlightPeriod flightPeriod = new FlightPeriod(timestamp, directedOneWaySimpleSearch.getOutwardDateRange());
        return getOneWayDirectFlights(directedOneWaySimpleSearch, flightPeriod);
    }

    private List<FlightGroup> getOneWayStopoverFlights(DirectedOneWaySimpleSearch directedOneWaySimpleSearch) {

        List<FlightGroup> result = new ArrayList<FlightGroup>();

        //master leg means 1st for outward and 2nd for return flight
        //date and time of start/end of a trip are date and time of correspondent master legs departure/arrival
        DirectedOneWaySimpleSearch masterLegSimpleSearch = new DirectedOneWaySimpleSearch(directedOneWaySimpleSearch);
        if(directedOneWaySimpleSearch.isOutward())
            masterLegSimpleSearch.setAirportToId(null);
        else
            masterLegSimpleSearch.setAirportFromId(null);

        List<FlightGroup> masterLegFlights = getOneWayDirectFlights(masterLegSimpleSearch);

        for(FlightGroup masterLegFlightGroup : masterLegFlights) {
            Flight masterLegFlight = masterLegFlightGroup.get(0);

            DirectedOneWaySimpleSearch slaveLegSimpleSearch = new DirectedOneWaySimpleSearch(directedOneWaySimpleSearch);

            if(directedOneWaySimpleSearch.isOutward())
                slaveLegSimpleSearch.setAirportFromId(masterLegFlight.getAirportTo().getId());
            else
                slaveLegSimpleSearch.setAirportToId(masterLegFlight.getAirportFrom().getId());

            List<FlightGroup> slaveLegFlights = getOneWayDirectFlights(slaveLegSimpleSearch, new FlightPeriod(masterLegFlight, directedOneWaySimpleSearch.isOutward()));

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

    private List<FlightGroup> getOneWayFlights(DirectedOneWaySimpleSearch directedOneWaySimpleSearch) {
        List<FlightGroup> result = new ArrayList<>();
        result.addAll(getOneWayDirectFlights(directedOneWaySimpleSearch));
        result.addAll(getOneWayStopoverFlights(directedOneWaySimpleSearch));
        return result;
    }

    public List<FlightGroup> getFlights(SimpleSearch simpleSearch) {

        DirectedOneWaySimpleSearch outwardSearch = new DirectedOneWaySimpleSearch(simpleSearch, DirectedOneWaySimpleSearch.OUTWARD);
        List<FlightGroup> outwardFlightGroups = getOneWayFlights(outwardSearch);
        for(FlightGroup outwardFlightGroup : outwardFlightGroups)
            outwardFlightGroup.setOutwardSize(outwardFlightGroup.size());

        DirectedOneWaySimpleSearch returnSearch = new DirectedOneWaySimpleSearch(
                simpleSearch.getAirportToId(),
                simpleSearch.getAirportFromId(),
                simpleSearch.getReturnDate(),
                simpleSearch.getReturnDateRange(),
                DirectedOneWaySimpleSearch.RETURN
        );

        List<FlightGroup> result = new ArrayList<FlightGroup>();

        if(simpleSearch.getTwoWayFlight()) {
            List<FlightGroup> returnFlightGroups = getOneWayFlights(returnSearch);

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

        Collections.sort(result);
        return result;
    }

    @Override
    public void bulkUpdateFlights(List<Flight> flights) {
        for(Flight flight : flights) {
            flightDAO.create(flight);
        }
    }

}
