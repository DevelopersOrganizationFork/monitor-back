package org.developers.monitor.rest.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sebastian.alberski on 2015-06-10.
 */
public class DateConverter {
    
    public static final String DATE_FORMAT = "dd/MM/yyyy hh:mm";
    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(DATE_FORMAT);
    
    public static Date convertStringToDate(String dateValue) {
        Date date;
        try {
            date = DATE_FORMATTER.parse(dateValue);
        } catch (ParseException e) {
            throw new RuntimeException("Allowed format date: " + DATE_FORMAT);
        }
        return date;
    }
    
}
