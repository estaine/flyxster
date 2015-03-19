package com.estaine.flyxster.dao;

import com.estaine.flyxster.model.Airport;
import com.estaine.flyxster.model.City;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by AndreyRykhalsky on 02.02.2015.
 */
@Repository
public class AirportDAOImpl extends GenericDAOImpl<Airport> implements AirportDAO {

    @Override
    public List<Airport> getAirportsBySuggestion(String userInput) {
        String queryBody = "FROM Airport WHERE ((code LIKE :userInputPattern) OR (name LIKE :userInputPattern))";
        String userInputPattern = "%" + userInput + "%";
        Query query = getCurrentSession().createQuery(queryBody);
        query.setParameter("userInputPattern", userInputPattern);
        List<Airport> list = query.list();
        return (list.isEmpty()) ? null : list;
    }

    @Override
    public Airport getAirportByCity(City city) {
        if (city == null)
            return null;
        String queryBody = "SELECT airport FROM Airport airport JOIN airport.cities airportCity WHERE airportCity.id = :cityId";
        Query query = getCurrentSession().createQuery(queryBody);
        query.setParameter("cityId", city.getId());
        List<Airport> list = query.list();
        return (list.isEmpty()) ? null : list.get(0);
    }
}
