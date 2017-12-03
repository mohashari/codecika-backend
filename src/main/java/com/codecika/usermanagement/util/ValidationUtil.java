package com.codecika.usermanagement.util;

import java.util.Collection;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Created by rizkimuhammad on 4/5/17.
 */
public class ValidationUtil {

    public static final String NUMBER_PATTERN = "\\d+";
    public static final String USERNAME_PATTERN = "^[a-z0-9_-]{3,10}$";
    public static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
    public static final String VEHICLE_PATTERN = "^([A-Z]{1,2}\\s[0-9]{1,4}\\s[A-Z]{0,3}|^[A-Z]{1,2}\\s[0-9]{1,4}[A-Z]{0,3})$";


    public static boolean isNotEmptyOrNull(Object obj) {
        if(obj == null) return false;
        if(obj instanceof String)
            return !((String) obj).isEmpty();
        else if (obj instanceof Collection)
            return ((Collection<?>) obj).size() != 0;
        return true;
    }

    public static boolean isEmptyOrNull(Object obj) {
        if(obj == null) return true;
        if(obj instanceof String)
            return ((String) obj).isEmpty();
        else if (obj instanceof Collection)
            return ((Collection<?>) obj).size() == 0;
        return false;
    }

    public static boolean regexValidate(String value, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);

        return matcher.matches();
    }
}
