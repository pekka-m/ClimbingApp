package me.climbingti.climbingtrainer.util;

/**
 * Created by Aleksi on 28.12.2015.
 * in me.climbingti.climbingtrainer.common
 */
public class Utils {
    //returns the greatest value of given parameters
    public static float calculateRadiusOffset(
            float strokeSize, float dotStrokeSize, float markerStrokeSize) {
        return Math.max(strokeSize, Math.max(dotStrokeSize, markerStrokeSize));
    }
}