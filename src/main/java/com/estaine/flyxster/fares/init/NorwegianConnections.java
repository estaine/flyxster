package com.estaine.flyxster.fares.init;

import com.estaine.flyxster.model.Airline;
import com.estaine.flyxster.model.Airport;
import com.estaine.flyxster.model.Connection;
import com.estaine.flyxster.service.AirlineService;
import com.estaine.flyxster.service.AirportService;
import com.estaine.flyxster.service.ConnectionService;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by AndreyRykhalsky on 24.02.15.
 */
@Component
public class NorwegianConnections {

    @Autowired
    AirportService airportService;

    @Autowired
    ConnectionService connectionService;

    @Autowired
    AirlineService airlineService;

    public void loadConnections() {

        List<Airport> airports = airportService.getAllAirports();
        Airline norwegianAirline = airlineService.getAirlineByCode("DY");
        for(Airport airport : airports) {
            String htmlResponse = "";
            try {
                StringBuilder url = new StringBuilder("http://www.norwegian.com/en/flight/tips/?IncludeTransit=False&D_City=").append(airport.getCode());

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
                htmlResponse = result.toString();
            }
            catch(Exception e) { continue; }

            Document responseDoc = Jsoup.parse(htmlResponse);
            Elements destinations = responseDoc.select("div.cityName");
            String separator = "(";
            for(Element destination : destinations) {
                String destinationHtml = destination.html();
                int destIndex = destinationHtml.indexOf(separator);
                String destCode = destinationHtml.substring(destIndex + 1, destIndex + 4);
                Connection connection = connectionService.getConnectionByAirlineAndCodes(norwegianAirline, airport.getCode(), destCode);
                if(connection == null) {
                    Airport destAirport = airportService.getAirportByCode(destCode);

                    Airport firstAirport = (airport.getId() < destAirport.getId()) ? airport : destAirport;
                    Airport secondAirport = (airport.getId() < destAirport.getId()) ? destAirport : airport;

                    connectionService.addConnection(new Connection(norwegianAirline, firstAirport, secondAirport));
                }
            }

        }
    }

}
