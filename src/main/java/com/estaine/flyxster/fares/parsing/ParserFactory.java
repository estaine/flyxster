package com.estaine.flyxster.fares.parsing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;


/**
 * Created by AndreyRykhalsky on 23.02.15.
 */
@Component
public class ParserFactory {
    @Autowired
    private ApplicationContext applicationContext;


    private static String getClassName(String airlineName) {
        return airlineName + "Parser";
    }



    public Parser getParser(String airlineName) {
        return (Parser) applicationContext.getBean(getClassName(airlineName));
    }
}