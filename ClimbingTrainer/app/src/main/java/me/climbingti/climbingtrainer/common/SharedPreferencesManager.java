package me.climbingti.climbingtrainer.common;

import android.content.Context;
import android.content.SharedPreferences;

import me.climbingti.climbingtrainer.R;

/**
 * Created by Aleksi on 3.11.2015.
 */
public class SharedPreferencesManager implements SharedPreferencesManagerInterface {
    private Context context;
    private SharedPreferences prefs;
    private SharedPreferences manager;
    private SharedPreferences.Editor editor;
    private final String PREF_NAME = "me.climbingti.climbingtrainer.SETTINGS";
    private final String GRADING = "GRADING";
    private final String CAMPUS_SPACING = "SPACING";
    private final String NUMBEROFPRACTCES = "NUMBEROFPRACTICES";

    // map default values
    // CAMPUS SPACING - 22
    //

    public SharedPreferencesManager(Context context) {
        this.context = context;
        prefs = context.getSharedPreferences(PREF_NAME, 0);
        editor = prefs.edit();
    }

    @Override
    public int getGrading() {
       return prefs.getInt(GRADING, R.array.fontainebleau_grades);
    }

    @Override
    public int getCampusSpacing() {
        return prefs.getInt(CAMPUS_SPACING, 0);
    }

    @Override
    public int getNumberOfPracticesToShow(){
        return prefs.getInt(NUMBEROFPRACTCES, 3);
    }
    //0 = font, 1 = hueco
    @Override
    public void setGrading(int GradeScale) {
        editor.putInt(GRADING, GradeScale);
        editor.apply();
        editor.commit();
    }

    @Override
    public void setCampusSpacing(int spacing) {
        editor.putInt(CAMPUS_SPACING, spacing);
        editor.apply();
        editor.commit();
    }

    @Override
    public void setNumberOfPracticesToShow(int numberOfPracticesToShow){
        editor.putInt(NUMBEROFPRACTCES, numberOfPracticesToShow);
        editor.apply();
        editor.commit();
    }
}