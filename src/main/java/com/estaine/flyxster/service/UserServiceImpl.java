package com.estaine.flyxster.service;

import com.estaine.flyxster.dao.UserDAO;
import com.estaine.flyxster.model.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by AndreyRykhalsky on 13.02.15.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDAO userDAO;

    @Transactional
    @Override
    public User getUserByLogin(String login) {
           return userDAO.getByProperty("username", login);
    }

    @Override
    public void updateUser(User user) {
        userDAO.update(user);
    }
}
