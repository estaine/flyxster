package com.estaine.flyxster.service;

import com.estaine.flyxster.common.FlightGroup;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Created by AndreyRykhalsky on 10.03.15.
 */
public interface SearchLineService {

    @Transactional
    public int saveSearch(List<FlightGroup> flightGroups);

    @Transactional
    public List<FlightGroup> loadSearch(int searchId);
}
