package com.codecika.usermanagement.util;

import com.codecika.usermanagement.exception.UserManagementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yukibuwana on 3/31/17.
 */
public class DateUtils {
    private static final Logger log = LoggerFactory.getLogger(DateUtils.class);

    private static final String DEFAULT_FORMAT = "yyyy-MM-dd";
    public static final String TIMESTAMP = "yyyyMMddHHmmsss";

    public static Date stringToDate(String date, String format) {
        Date result;

        DateFormat dateFormat = new SimpleDateFormat(format);

        try {
            result = dateFormat.parse(date);
        } catch (ParseException e) {
            log.error(e.getMessage());
            throw new UserManagementException("Format Date " + format + " ex: " + dateToString(new Date(), format));
        }

        return result;
    }

    public static Date stringToDate(String date) {
        return stringToDate(date, DEFAULT_FORMAT);
    }

    public static String dateToString(Date date, String format) {
        String result;

        DateFormat dateFormat = new SimpleDateFormat(format);

        result = dateFormat.format(date);

        return result;
    }

    public static String dateToString(Date date) {
        return dateToString(date, DEFAULT_FORMAT);
    }

    public static Date reformatDate(Date date, String newFormat) {
        Date result;
        SimpleDateFormat dateFormat = new SimpleDateFormat(newFormat);

        try {
            String tempDate = dateFormat.format(date);
            result = dateFormat.parse(tempDate);
        } catch (ParseException e) {
            log.error(e.getMessage());
            throw new UserManagementException("Failed to reformat date into \"" + newFormat +"\"" );
        }

        return result;
    }
}
