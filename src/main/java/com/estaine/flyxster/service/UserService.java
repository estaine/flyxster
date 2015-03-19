package com.estaine.flyxster.service;

import com.estaine.flyxster.model.security.User;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by AndreyRykhalsky on 13.02.15.
 */
public interface UserService {
    @Transactional
    public User getUserByLogin(String login);

    @Transactional
    public void updateUser(User user);


}
