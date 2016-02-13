package me.climbingti.climbingtrainer.util;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Aleksi on 29.11.2015.
 */
public class DateConverter {
    // TODO: 31.12.2015 needs more TDD
    // TODO: 31.12.2015 change this to static one
    private DateFormat dateFormat;

    /**
     * Converts into dd. MM format
     * @param date date to be formatted
     * @return returns formatted date as String
     */
    public String convertIntoDayMonth(Date date){
        dateFormat = new SimpleDateFormat("dd. MMM", Locale.getDefault());
        return dateFormat.format(date);
    }

    /**
     * Converts into yyyy-MM-dd HH:mm:ss
     * @param date date to be formatted
     * @return returns formatted date as String
     */
    public String convertIntoISO8601(Date date){
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.getDefault());
        return dateFormat.format(date);
    }

    public Date parseIntoISO8601(String date){
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.getDefault());
        Date dateString;
        try {
            Log.d("DateConverter", "parseIntoISO8601:  date value: " + date);
            dateString = dateFormat.parse(date);
            return dateString;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String jotainpaskaa(String date ){
        DateFormat datea = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.getDefault());
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.getDefault());
        Date paskaa;
        String bullshit = null;
        try {
            paskaa = datea.parse(date);
            bullshit = dateFormat.format(paskaa);
        }
        catch (Exception e){

        }
        return bullshit;
    }



    /**
     * converts into HH:mm
     * @param date date to be formatted
     * @return returns formatted date as String
     */
    public String convertIntoHoursMinutes(Date date){
        dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        dateFormat.setTimeZone(TimeZone.getDefault());
        return dateFormat.format(date);
    }

    /**
     * Converts into yyyyMMddHHmm
     * @param date date to be formatted
     * @return formatted date as String
     */
    public String convertIntoDateTime(Date date){
        dateFormat = new SimpleDateFormat("yyyyMMddHHmm", Locale.getDefault());
        return dateFormat.format(date);
    }

    /**
     * Converts into yyyyMMdd
     * @param date date to be formatted
     * @return formatted date as String
     */
    public String convertIntoDate(Date date){
        dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        return dateFormat.format(date);
    }
}