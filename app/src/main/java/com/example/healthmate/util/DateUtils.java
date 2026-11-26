package com.example.healthmate.util;

import java.util.Locale;
import java.util.Date;
import java.text.SimpleDateFormat;
public class DateUtils {
    private static final String DATE_FORMAT = "yyyy-MM-dd";


            public static String today() {
                return new SimpleDateFormat(DATE_FORMAT, Locale.US).format(new Date());
            }
}
