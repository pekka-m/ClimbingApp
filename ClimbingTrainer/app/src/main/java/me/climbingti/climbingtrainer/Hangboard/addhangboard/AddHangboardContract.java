package me.climbingti.climbingtrainer.hangboard.addhangboard;

import me.climbingti.climbingtrainer.common.Collection;

/**
 * Created by Aleksi on 30.12.2015.
 * in me.climbingti.climbingtrainer.hangboard.addhangboard
 */
public interface AddHangboardContract {

    interface View{
        void showHangboardRepAdded();

        void showHangboardingSessions();

        void showDatabaseError();
    }

    interface Presenter{
        void addHangboardRep(String hangTime, String restTime, String Reps);

        Collection loadHangboardingSessions();

    }
}