package com.codecika.usermanagement.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by aska on 8/2/17.
 */
public class EmailCode {
    public static String codeEmail(){
    Date dt = new Date();
    SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddHHmmss");
    return ft.format(dt);
    }
}

