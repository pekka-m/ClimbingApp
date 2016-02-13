package me.climbingti.climbingtrainer.mainview.campus;

import android.support.v4.util.ArrayMap;

import java.util.Date;

import me.climbingti.climbingtrainer.common.Collection;

/**
 * Created by Aleksi on 31.12.2015.
 * in me.climbingti.climbingtrainer.mainview.campus
 */
public interface MainViewCampusContract {

    interface View{
        void showCampusReps(ArrayMap<Date, Collection> campusReps);
    }

    interface Presenter{
        void loadCampusReps();
    }
}