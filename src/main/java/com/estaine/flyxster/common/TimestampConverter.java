package com.estaine.flyxster.common;

import java.text.ParseException;
import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by AndreyRykhalsky on 05.02.2015.
 */
public class TimestampConverter {
    private static final String SEARCH_FORMAT = "dd MMM yyyy";
    public static final String RESULT_WEEKDAY_FORMAT = "EEE";
    public static final String RESULT_DATE_FORMAT = "dd.MM.yyyy";
    public static final String RESULT_TIME_FORMAT = "HH:mm";


    public static Timestamp toTimestamp(String string) {

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(SEARCH_FORMAT, Locale.ENGLISH);
            Date date = simpleDateFormat.parse(string);
            Timestamp timestamp = new Timestamp(date.getTime());
            return timestamp;

        }
        catch(ParseException e) {

        }
        return null;
    }

    public static String toResult(Timestamp timestamp, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.ENGLISH);
        Date date = new Date(timestamp.getTime());
        String resultDate = simpleDateFormat.format(date);
        return resultDate;
    }

    public static String getFlightDuration(Timestamp start, Timestamp end) {
        long diff = end.getTime() - start.getTime();
        long hours = diff / FlightLiterals.MILLISECONDS_PER_HOUR;
        long minutes = diff % FlightLiterals.MILLISECONDS_PER_HOUR / FlightLiterals.MILLISECONDS_PER_MINUTE;

        return hours + "h " + minutes + "min";
    }

}
