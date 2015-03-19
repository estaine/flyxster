package com.estaine.flyxster.dao;

import com.estaine.flyxster.model.security.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by AndreyRykhalsky on 11.02.15.
 */
@Repository
public class UserDAOImpl extends GenericDAOImpl <User> implements UserDAO {
}
