package com.estaine.flyxster.dao;

import com.estaine.flyxster.model.SearchLine;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by AndreyRykhalsky on 10.03.15.
 */
@Repository
public class SearchLineDAOImpl extends GenericDAOImpl <SearchLine> implements SearchLineDAO  {

    @Override
    public List<SearchLine> listBySearchId(int searchId) {
        Criteria criteria = buildCriteria(buildPropertyMap("searchId", searchId));
        criteria.addOrder(Order.asc("flightGroupIndex")).addOrder(Order.asc("flightIndex"));
        return criteria.list();
    }

}
