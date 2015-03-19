package com.estaine.flyxster.fares.parsing;

import com.estaine.flyxster.common.FlightLiterals;
import com.estaine.flyxster.common.TimestampConverter;
import com.estaine.flyxster.model.Airline;
import com.estaine.flyxster.model.Airport;
import com.estaine.flyxster.model.Flight;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.*;

/**
 * Created by AndreyRykhalsky on 17.02.15.
 */

@Component("norwegianParser")
public class NorwegianParserImpl extends ParserImpl implements Parser {

    @Override
    public List<Flight> parse(String response) {

        Document responseDoc = Jsoup.parse(response);
        Elements flightTableList = responseDoc.select("div.sectionboxavaday");
        if((flightTableList == null) || (flightTableList.isEmpty()))
            return null;
        Element flightTable = flightTableList.get(0);
        String flightDateCell = flightTable.select(".layoutcell").get(1).html();


        Elements flightRows = flightTable.select("tr.rowinfo1");

        Timestamp flightDate = TimestampConverter.toTimestamp(flightDateCell.substring(flightDateCell.lastIndexOf(";") + 1), "d. MMM yyyy", new Locale("en"));
        Airline airline = airlineService.getAirlineByCode("DY");

        List<Flight> flights = new ArrayList();
        for(Element flightRow : flightRows) {
            Flight flight = new Flight();
            flight.setAirline(airline);
            try {
                flight.setPrice(Double.parseDouble(flightRow.select("label").get(0).html()));
            }
            catch(NumberFormatException|IndexOutOfBoundsException e) {
                continue;
            }
            String norwegianFlightCode = flightRow.select(".radio-ajax").get(0).val();
            int airportsEndMark = norwegianFlightCode.indexOf("|", 2);

            Airport airportFrom = airportService.getAirportByCode(norwegianFlightCode.substring(airportsEndMark - 6, airportsEndMark - 3));
            Airport airportTo = airportService.getAirportByCode(norwegianFlightCode.substring(airportsEndMark - 3, airportsEndMark));

            Timestamp localDepartureDatetime = new Timestamp(flightDate.getTime() + TimestampConverter.toTimestamp(flightRow.select(".depdest > div").get(0).html(), "HH:mm").getTime());
            flight.setDepartureDatetime(TimestampConverter.toUTCFromLocal(localDepartureDatetime, TimeZone.getTimeZone(airportFrom.getTimezone())));

            long nextDayArrivalOffset = 0;
            if(!flightRow.select(".offsetdays").isEmpty())
                nextDayArrivalOffset = FlightLiterals.MILLISECONDS_PER_DAY;

            Timestamp localArrivalDatetime = new Timestamp(flightDate.getTime() + TimestampConverter.toTimestamp(flightRow.select(".arrdest > div").get(0).html(), "HH:mm").getTime() + nextDayArrivalOffset);
            flight.setArrivalDatetime(TimestampConverter.toUTCFromLocal(localArrivalDatetime, TimeZone.getTimeZone(airportTo.getTimezone())));

            flight.setNumber(flightRow.select(".depdest").get(0).attr("title").substring(9));


            flight.setAirportFrom(airportFrom);
            flight.setAirportTo(airportTo);
            flight.setAdded(new Timestamp((new Date()).getTime()));
            flights.add(flight);
        }

        return flights;
    }
}
