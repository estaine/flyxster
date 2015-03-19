package com.estaine.flyxster.fares.requesting;

import com.estaine.flyxster.common.TimestampConverter;
import com.estaine.flyxster.dto.RemoteSearchParameterSet;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by AndreyRykhalsky on 17.02.15.
 */

@Component
public class NorwegianRequestor implements Requestor {

    @Override
    public String request(RemoteSearchParameterSet requestParams) {
        try{
        StringBuilder url = new StringBuilder("http://www.norwegian.com/en/flight/select-flight/?TripType=1&IncludeTransit=false");
        url.append("&D_City=").append(requestParams.getAirportFromCode())
                .append("&A_City=").append(requestParams.getAirportToCode())
                .append("&D_Day=").append(TimestampConverter.toResult(requestParams.getFlightDate(),"d"))
                .append("&D_Month=").append(TimestampConverter.toResult(requestParams.getFlightDate(),"yyyyMM"));

        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url.toString());

        request.addHeader("User-Agent", "Mozilla/5.0");

        HttpResponse response = client.execute(request);


        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        return result.toString();
        }
        catch(Exception e) {}

        return null;

    }
}
