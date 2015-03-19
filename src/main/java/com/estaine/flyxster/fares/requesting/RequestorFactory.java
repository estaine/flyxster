package com.estaine.flyxster.fares.requesting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by AndreyRykhalsky on 24.02.15.
 */
@Component
public class RequestorFactory {

    @Autowired
    private ApplicationContext applicationContext;


    private static String getClassName(String airlineName) {
        return airlineName + "Requestor";
    }

    public Requestor getRequestor(String airlineName) {
        return (Requestor) applicationContext.getBean(getClassName(airlineName));
    }
}
