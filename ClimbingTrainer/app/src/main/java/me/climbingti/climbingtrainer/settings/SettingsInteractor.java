package me.climbingti.climbingtrainer.settings;

import android.util.Log;

import me.climbingti.climbingtrainer.R;
import me.climbingti.climbingtrainer.common.SharedPreferencesManagerInterface;

/**
 * Created by Aleksi on 15.11.2015.
 */
public class SettingsInteractor {

    private SharedPreferencesManagerInterface manager;

    public SettingsInteractor(SharedPreferencesManagerInterface manager) {
        this.manager = manager;
    }

    //0 = font, 1 = hueco
    public void switchGradingScale(int scale) {
        switch (scale) {
            case 0:
                manager.setGrading(R.array.fontainebleau_grades);
                break;
            case 1:
                manager.setGrading(R.array.hueco_grades);
                break;
            default:
                manager.setGrading(R.array.fontainebleau_grades);
                break;
        }
    }

    //0 = moon, 1 = moon half, 2 = metolius
    public void switchCampusSpacing(int spacing) {
        Log.d("SPACING", Integer.toString(spacing));
        manager.setCampusSpacing(spacing);
    }

    public int getGradingScale() {
        switch (manager.getGrading()) {
            case R.array.fontainebleau_grades:
                return 0;
            case R.array.hueco_grades:
                return 1;
            default:
                return 0;
        }
    }

    public int getCampusSpacing() {
        return manager.getCampusSpacing();
    }

    public void switchMeasurementUnits() {
        // TODO: 1.1.2016 fill out empty method stub
    }

    public void setGoogleFitSync() {
        // TODO: 1.1.2016 fill out empty method stub
    }


    public void getGoogleFitSyncState() {
        // TODO: 1.1.2016 fill out empty method stub
    }

    public void getMeasurementUnits() {
        // TODO: 1.1.2016 fill out empty method stub
    }
}