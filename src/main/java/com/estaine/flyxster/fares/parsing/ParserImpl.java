package com.estaine.flyxster.fares.parsing;

import com.estaine.flyxster.service.AirlineService;
import com.estaine.flyxster.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by AndreyRykhalsky on 23.02.15.
 */
@Component
public abstract class ParserImpl {

    @Autowired
    protected AirlineService airlineService;

    @Autowired
    protected AirportService airportService;

}
