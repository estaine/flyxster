package com.estaine.flyxster.common;

import com.estaine.flyxster.model.Airport;

import java.sql.Time;
import java.text.ParseException;
import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by AndreyRykhalsky on 05.02.2015.
 */
public class TimestampConverter {
    private static final String SEARCH_FORMAT = "d MMM yyyy";
    public static final String RESULT_WEEKDAY_FORMAT = "EEE";
    public static final String RESULT_DATE_FORMAT = "dd.MM.yyyy";
    public static final String RESULT_TIME_FORMAT = "HH:mm";

    public static Locale locale;

    public static Timestamp toTimestamp(String string, String format, Locale locale) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, locale);
            Date date = simpleDateFormat.parse(string);
            Timestamp timestamp = new Timestamp(date.getTime());
            return timestamp;

        }
        catch(ParseException e) {

        }
        return null;
    }


    public static Timestamp toTimestamp(String string, String format) {
        return toTimestamp(string, format, locale);
    }


    public static Timestamp toTimestamp(String string) {

       return toTimestamp(string, SEARCH_FORMAT);
    }

    public static String toResult(Timestamp timestamp, String format, TimeZone timeZone) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, locale);
        simpleDateFormat.setTimeZone(timeZone);
        Date date = new Date(timestamp.getTime());
        return simpleDateFormat.format(date);
    }

    public static String toResult(Timestamp timestamp, String format) {
        return toResult(timestamp, format, TimeZone.getTimeZone("Etc/UTC"));
    }

    public static String getFlightDuration(Timestamp start, Timestamp end) {

        long diff = end.getTime() - start.getTime();
        long hours = diff / FlightLiterals.MILLISECONDS_PER_HOUR;
        long minutes = diff % FlightLiterals.MILLISECONDS_PER_HOUR / FlightLiterals.MILLISECONDS_PER_MINUTE;

        return hours + "h " + minutes + "min";
    }

    public static Timestamp toUTCFromLocal(Timestamp local, TimeZone timeZone) {
        long localTime = local.getTime();
        long preOffset = timeZone.getOffset(localTime);
        long offset = timeZone.getOffset(localTime - preOffset);
        return new Timestamp(localTime - offset);
    }

}
