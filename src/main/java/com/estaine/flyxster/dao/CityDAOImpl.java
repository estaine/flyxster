package com.estaine.flyxster.dao;

import com.estaine.flyxster.model.City;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by AndreyRykhalsky on 02.02.2015.
 */
@Repository
public class CityDAOImpl extends GenericDAOImpl <City> implements CityDAO {
}
