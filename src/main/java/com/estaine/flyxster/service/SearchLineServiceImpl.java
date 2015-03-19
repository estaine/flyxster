package com.estaine.flyxster.service;

import com.estaine.flyxster.common.FlightGroup;
import com.estaine.flyxster.dao.SearchDAO;
import com.estaine.flyxster.dao.SearchLineDAO;
import com.estaine.flyxster.model.Flight;
import com.estaine.flyxster.model.Search;
import com.estaine.flyxster.model.SearchLine;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by AndreyRykhalsky on 10.03.15.
 */
@Service
public class SearchLineServiceImpl implements SearchLineService {

    @Autowired
    SearchDAO searchDAO;

    @Autowired
    SearchLineDAO searchLineDAO;


    private List<FlightGroup> toFlightGroups(List<SearchLine> searchLines) {
        List<FlightGroup> result = new ArrayList<>();
        int flightGroupIndex = 0;
        FlightGroup flightGroup = new FlightGroup();
        for(SearchLine searchLine : searchLines) {
            if(searchLine.getFlightGroupIndex() != flightGroupIndex) {
                result.add(flightGroup);
                flightGroup = new FlightGroup();
            }
            Hibernate.initialize(searchLine.getFlight());
            flightGroup.add(searchLine.getFlight());
        }
        return result;
    }

    private List<SearchLine> toSearchLines(List<FlightGroup> flightGroups, int searchId) {
        List<SearchLine> result = new ArrayList<>();
        for(int flightGroupIndex = 0; flightGroupIndex < flightGroups.size(); flightGroupIndex++) {
            FlightGroup flightGroup = flightGroups.get(flightGroupIndex);
            for(int flightIndex = 0; flightIndex < flightGroup.size(); flightIndex++) {
                Flight flight = flightGroup.get(flightIndex);
                result.add(new SearchLine(searchId, flightGroupIndex, flightIndex, flight));
            }
        }
        return result;
    }


    @Override
    public int saveSearch(List<FlightGroup> flightGroups) {
        int searchId = searchDAO.create(new Search());
        List<SearchLine> searchLines = toSearchLines(flightGroups, searchId);
        for(SearchLine searchLine : searchLines)
            searchLineDAO.create(searchLine);
        return searchId;

    }

    @Override
    public List<FlightGroup> loadSearch(int searchId) {
        List<SearchLine> searchLines = searchLineDAO.listByProperty("searchId", searchId);
        return toFlightGroups(searchLines);
    }
}
