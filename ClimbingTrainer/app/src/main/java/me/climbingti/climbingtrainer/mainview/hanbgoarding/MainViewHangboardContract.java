package me.climbingti.climbingtrainer.mainview.hanbgoarding;

import android.support.v4.util.ArrayMap;

import java.util.Date;

import me.climbingti.climbingtrainer.common.Collection;

/**
 * Created by Aleksi on 31.12.2015.
 * in me.climbingti.climbingtrainer.mainview.hanbgoarding
 */
public interface MainViewHangboardContract {

    interface View{
        void showHangboardReps(ArrayMap<Date, Collection> hangboardReps);
    }

    interface Presenter{
        void loadHangboardReps();
    }
}
