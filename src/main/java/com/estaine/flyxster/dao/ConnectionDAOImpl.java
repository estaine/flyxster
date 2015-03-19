package com.estaine.flyxster.dao;

import com.estaine.flyxster.model.Airline;
import com.estaine.flyxster.model.Airport;
import com.estaine.flyxster.model.Connection;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by AndreyRykhalsky on 24.02.15.
 */
@Repository
public class ConnectionDAOImpl extends GenericDAOImpl<Connection> implements ConnectionDAO {

}
