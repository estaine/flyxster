package com.estaine.flyxster.dao;

import com.estaine.flyxster.model.Airport;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by AndreyRykhalsky on 02.02.2015.
 */
@Repository
public class AirportDAOImpl extends GenericDAOImpl <Airport> implements AirportDAO {

    @Override
    public List<Airport> getAirportsBySuggestion(String userInput) {
        Session session = sessionFactory.getCurrentSession();
        String queryBody = "FROM Airport WHERE ((code LIKE :userInputPattern) OR (name LIKE :userInputPattern))";
        String userInputPattern = "%" + userInput + "%";
        Query query = session.createQuery(queryBody);
        query.setParameter("userInputPattern", userInputPattern);
        List<Airport> list = query.list();
        return (list.isEmpty()) ? null : list;
    }
}
