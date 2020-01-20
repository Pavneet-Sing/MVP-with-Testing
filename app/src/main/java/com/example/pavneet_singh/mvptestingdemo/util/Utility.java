package com.example.pavneet_singh.mvptestingdemo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Pavneet Singh on 8/30/2017.
 */

public final class Utility {

    public static final SimpleDateFormat tf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    private Utility() {
        throw new IllegalStateException("Cannot Instantiate");
    }

    public static String convertMinutesToDuration(int duratonInMinutes) {
        int hours = duratonInMinutes / 60; //since both are ints, you get an int
        int minutes = duratonInMinutes % 60;
        return String.format(Locale.getDefault(), "%d Hours %02d Minutes", hours, minutes);
    }

    public static Date convertStringToDate(String yyyyddmm) throws ParseException {
        return tf.parse(yyyyddmm);
    }


}
