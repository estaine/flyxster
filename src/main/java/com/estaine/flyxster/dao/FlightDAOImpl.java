package com.estaine.flyxster.dao;

import com.estaine.flyxster.dto.SimpleFlightParameterSet;

import com.estaine.flyxster.model.Flight;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by AndreyRykhalsky on 02.02.2015.
 */
@Repository
public class FlightDAOImpl extends GenericDAOImpl <Flight> implements FlightDAO {

    @Override
    public List<Flight> getFlightsBySimpleParameterSet(SimpleFlightParameterSet simpleFlightParameterSet) {
        Session session = sessionFactory.getCurrentSession();
        StringBuilder queryBody = new StringBuilder("FROM Flight WHERE ((");

        if(simpleFlightParameterSet.isOutwardFlight())
            queryBody.append(" departureDatetime");
        else
            queryBody.append(" arrivalDatetime");
        queryBody.append(" BETWEEN :periodStart AND :periodEnd)");

        if(simpleFlightParameterSet.getAirportFromId() != null)
            queryBody.append(" AND (airportFrom.id = :airportFromId)");
        if(simpleFlightParameterSet.getAirportToId() != null)
            queryBody.append(" AND (airportTo.id = :airportToId)");
        queryBody.append(")");


        Query query = session.createQuery(queryBody.toString());

        query.setParameter("periodStart", simpleFlightParameterSet.getFlightPeriod().getStart());
        query.setParameter("periodEnd", simpleFlightParameterSet.getFlightPeriod().getEnd());

        if(simpleFlightParameterSet.getAirportFromId() != null)
            query.setParameter("airportFromId", simpleFlightParameterSet.getAirportFromId());
        if(simpleFlightParameterSet.getAirportToId() != null)
            query.setParameter("airportToId", simpleFlightParameterSet.getAirportToId());

        List<Flight> list = query.list();
        return list;
    }
}
