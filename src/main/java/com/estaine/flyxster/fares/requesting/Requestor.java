package com.estaine.flyxster.fares.requesting;


import com.estaine.flyxster.dto.RemoteSearchParameterSet;

/**
 * Created by AndreyRykhalsky on 17.02.15.
 */
public interface Requestor {
    public String request(RemoteSearchParameterSet requestParams);
}
