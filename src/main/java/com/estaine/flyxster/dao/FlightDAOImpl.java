package com.estaine.flyxster.dao;

import com.estaine.flyxster.dto.SimpleFlightParameterSet;

import com.estaine.flyxster.model.Flight;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by AndreyRykhalsky on 02.02.2015.
 */
@Repository
public class FlightDAOImpl extends GenericDAOImpl<Flight> implements FlightDAO {

    @Override
    public List<Flight> getFlightsBySimpleParameterSet(SimpleFlightParameterSet simpleFlightParameterSet) {
        StringBuilder queryBody = new StringBuilder("SELECT common FROM Flight common WHERE ( (");

        if (simpleFlightParameterSet.isOutwardFlight())
            queryBody.append("common.departureDatetime");
        else
            queryBody.append(" common.arrivalDatetime");
        queryBody.append(" BETWEEN :periodStart AND :periodEnd)");

        if (simpleFlightParameterSet.getAirportFromId() != null)
            queryBody.append(" AND (common.airportFrom.id = :airportFromId)");
        if (simpleFlightParameterSet.getAirportToId() != null)
            queryBody.append(" AND (common.airportTo.id = :airportToId)");

        queryBody.append(" AND (common.added = ");
        queryBody.append(" (SELECT max(groupcore.added) FROM Flight groupcore WHERE (");
        queryBody.append(" (common.airline = groupcore.airline)");
        queryBody.append(" AND (groupcore.number = common.number)");
        queryBody.append(" AND (groupcore.airportFrom = common.airportFrom)");
        queryBody.append(" AND (groupcore.airportTo = common.airportTo)");
        queryBody.append(" AND (groupcore.departureDatetime = common.departureDatetime)");
        queryBody.append(" AND (groupcore.arrivalDatetime = common.arrivalDatetime)");
        queryBody.append(") ) ) )");
                // (SELECT max(groupcore.added) FROM Flight groupcore WHERE ( (common.airline = groupcore.airline) AND (common.number = groupcore.number) AND (common.airportFrom = groupcore.airportFrom) AND (common.airportTo = groupcore.airportTo) AND (common.departureDatetime = groupcore.departureDatetime) ) ))");


        Query query = getCurrentSession().createQuery(queryBody.toString());

        query.setParameter("periodStart", simpleFlightParameterSet.getFlightPeriod().getStart());
        query.setParameter("periodEnd", simpleFlightParameterSet.getFlightPeriod().getEnd());

        if (simpleFlightParameterSet.getAirportFromId() != null)
            query.setParameter("airportFromId", simpleFlightParameterSet.getAirportFromId());
        if (simpleFlightParameterSet.getAirportToId() != null)
            query.setParameter("airportToId", simpleFlightParameterSet.getAirportToId());


        List<Flight> list = query.list();

        return list;
    }
}
