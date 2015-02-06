package com.estaine.flyxster.controllers;

import com.estaine.flyxster.common.FlightGroup;
import com.estaine.flyxster.dto.SimpleSearch;
import com.estaine.flyxster.model.Flight;
import com.estaine.flyxster.service.AirportService;
import com.estaine.flyxster.service.FlightService;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@Controller
public class PageController {

    private static final Logger logger = LoggerFactory.getLogger(PageController.class);

    @Autowired
    private FlightService flightService;

    @Autowired
    private AirportService airportService;

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("index", "simpleSearchForm", new SimpleSearch());
    }

    @RequestMapping(value = {"/search"}, method = RequestMethod.POST)
    public ModelAndView search(@ModelAttribute SimpleSearch simpleSearch) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("search");
        modelAndView.addObject("searchCriteria", simpleSearch);

        Set<FlightGroup> flightGroups = flightService.getFlights(simpleSearch);

        modelAndView.addObject("flightGroups", flightGroups);

        return modelAndView;
    }

    @RequestMapping(value="/typeahead", method = RequestMethod.POST)
    public @ResponseBody String typeahead(@RequestParam String query) {

        String result = airportService.getAirportsBySuggestionJSON(query);
        return result;
    }

    public FlightService getFlightService() {
        return flightService;
    }

    public void setFlightService(FlightService flightService) {
        this.flightService = flightService;
    }

    public AirportService getAirportService() {
        return airportService;
    }

    public void setAirportService(AirportService airportService) {
        this.airportService = airportService;
    }
}