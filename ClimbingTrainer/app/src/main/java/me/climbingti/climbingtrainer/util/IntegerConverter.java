package me.climbingti.climbingtrainer.util;

/**
 * Created by H4198 on 3.12.2015.
 */
public class IntegerConverter {

    public static int safeLongToInt(long l){
        if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE){
            throw new IllegalArgumentException(l + "cannot be cast to int without changing its value");

        }
        return (int) l;
    }
}