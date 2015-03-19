package com.estaine.flyxster.fares.parsing;

import com.estaine.flyxster.model.Flight;
import com.estaine.flyxster.service.AirlineService;
import com.estaine.flyxster.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by AndreyRykhalsky on 17.02.15.
 */

public interface Parser {

    public List<Flight> parse(String response);

}
