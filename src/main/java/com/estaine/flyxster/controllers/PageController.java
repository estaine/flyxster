package com.estaine.flyxster.controllers;

import com.estaine.flyxster.common.FlightGroup;
import com.estaine.flyxster.common.TimestampConverter;
import com.estaine.flyxster.dto.PagingInfo;
import com.estaine.flyxster.dto.PasswordChange;
import com.estaine.flyxster.dto.PersonalDataChange;
import com.estaine.flyxster.dto.SimpleSearch;
import com.estaine.flyxster.model.Airport;
import com.estaine.flyxster.model.security.User;
import com.estaine.flyxster.service.AirportService;
import com.estaine.flyxster.service.FlightService;
import com.estaine.flyxster.service.SearchLineService;
import com.estaine.flyxster.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.context.annotation.Scope;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

@Controller
@SessionAttributes({"searchResults", "simpleSearchForm"})
public class PageController {

    private static final Logger logger = LoggerFactory.getLogger(PageController.class);

    @Autowired
    private FlightService flightService;

    @Autowired
    private AirportService airportService;

    @Autowired
    UserDetailsManager userDetailsManager;

    @Autowired
    UserService userService;

    @Autowired
    SearchLineService searchLineService;

    @Resource
    private LocaleResolver localeResolver;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public ModelAndView index()
    {
        ModelAndView modelAndView = new ModelAndView("index");
        SimpleSearch simpleSearch =  new SimpleSearch();
        String homeAirportNameCode = "";


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.getUserByLogin(auth.getName());
        if(currentUser != null)
        {
            String cityName = currentUser.getCity();
            Airport homeAirport = airportService.getAirportByCityName(cityName);
            if(homeAirport != null) {
                simpleSearch.setAirportFromId(homeAirport.getId());
                homeAirportNameCode = homeAirport.getName() + " (" + homeAirport.getCode() + ")";
            }
        }


        modelAndView.addObject("homeAirportNameCode", homeAirportNameCode);
        modelAndView.addObject("simpleSearchForm", simpleSearch);
        modelAndView.addObject("locale", LocaleContextHolder.getLocale());
        TimestampConverter.locale = LocaleContextHolder.getLocale();
        return modelAndView;
    }


    private ModelAndView fillSearchPageModel(int pageNumber, int pageSize, SimpleSearch simpleSearch) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("search");
        modelAndView.addObject("simpleSearchForm", simpleSearch);
        modelAndView.addObject("pageNumber", pageNumber);
        modelAndView.addObject("pageSize", pageSize);
        /*
            TODO: Move airport name logic from controller to service
        */
        String airportFromNameCode = airportService.getAirportNameCodeById(simpleSearch.getAirportFromId());
        String airportToNameCode = airportService.getAirportNameCodeById(simpleSearch.getAirportToId());
        modelAndView.addObject("airportFromNameCode", airportFromNameCode);
        modelAndView.addObject("airportToNameCode", airportToNameCode);
        modelAndView.addObject("locale", LocaleContextHolder.getLocale());
        return modelAndView;
    }

    @RequestMapping(value= {"/search"}, method = RequestMethod.GET)
    public ModelAndView changePage(@RequestParam(value = "pageNumber") int pageNumber, @RequestParam(value="pageSize") int pageSize, @ModelAttribute SimpleSearch simpleSearch) {
        return fillSearchPageModel(pageNumber, pageSize, simpleSearch);

    }

    @RequestMapping(value = {"/search"}, method = RequestMethod.POST)
    public ModelAndView search(@ModelAttribute SimpleSearch simpleSearch) {
        List<FlightGroup> flightGroups = flightService.getFlights(simpleSearch);

        PagedListHolder pagedListHolder = new PagedListHolder(flightGroups);
        ModelAndView modelAndView = fillSearchPageModel(0, 15, simpleSearch);
        modelAndView.addObject("searchResults", pagedListHolder);

        return modelAndView;
    }

    @RequestMapping(value="/typeahead", method = RequestMethod.POST)
    public @ResponseBody String typeahead(@RequestParam String query) {

        String result = airportService.getAirportsBySuggestionJSON(query);
        return result;
    }

    @RequestMapping(value="/profile")
    public ModelAndView profile()
    {
        ModelAndView modelAndView = new ModelAndView("profile");
        modelAndView.addObject("passwordChange", new PasswordChange());
        PersonalDataChange personalDataChange = new PersonalDataChange();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.getUserByLogin(auth.getName());
        personalDataChange.setCity(currentUser.getCity());
        personalDataChange.setLocale(currentUser.getLanguage());
        modelAndView.addObject("personalDataChange", personalDataChange);
        modelAndView.addObject("locale", LocaleContextHolder.getLocale());

        return modelAndView;
    }

    @RequestMapping(value="/change-password")
    public ModelAndView changePassword(@ModelAttribute PasswordChange passwordChange)
    {
        ModelAndView modelAndView = profile();
        userDetailsManager.changePassword(passwordChange.getOldPassword(), passwordChange.getNewPassword());
        modelAndView.addObject("passwordChanged", true);
        passwordChange.setNewPassword("");
        passwordChange.setOldPassword("");
        return modelAndView;
    }

    @RequestMapping(value="/change-personal-data")
    public ModelAndView changePersonalData(@ModelAttribute PersonalDataChange personalDataChange)
    {
        ModelAndView modelAndView = profile();
        /*
            TODO: Refactor to avoid copy-paste logic for currentUser accessing
         */
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.getUserByLogin(auth.getName());
        currentUser.setCity(personalDataChange.getCity());
        currentUser.setLanguage(personalDataChange.getLocale());
        userService.updateUser(currentUser);
        modelAndView.addObject("personalDataChanged", true);

        personalDataChange.setCity(currentUser.getCity());
        personalDataChange.setLocale(currentUser.getLanguage());
        modelAndView.addObject("personalDataChange", personalDataChange);
        LocaleContextHolder.setLocale(new Locale(currentUser.getLanguage()));
        return modelAndView;
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

    public UserDetailsManager getUserDetailsManager() {
        return userDetailsManager;
    }

    public void setUserDetailsManager(UserDetailsManager userDetailsManager) {
        this.userDetailsManager = userDetailsManager;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}