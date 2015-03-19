package com.estaine.flyxster.dao;

import com.estaine.flyxster.model.SearchLine;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by AndreyRykhalsky on 10.03.15.
 */
public interface SearchLineDAO extends GenericDAO <SearchLine> {
    public List<SearchLine> listBySearchId(int searchId);
}
