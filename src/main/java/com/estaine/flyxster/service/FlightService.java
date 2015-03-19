package com.estaine.flyxster.service;

import com.estaine.flyxster.common.FlightGroup;
import com.estaine.flyxster.dto.SimpleSearch;
import com.estaine.flyxster.model.Flight;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;


/**
 * Created by AndreyRykhalsky on 03.02.2015.
 */
public interface FlightService {
    @Transactional
    public List<FlightGroup> getFlights(SimpleSearch simpleSearch);

    @Transactional
    public void bulkUpdateFlights(List<Flight> flights);
}
