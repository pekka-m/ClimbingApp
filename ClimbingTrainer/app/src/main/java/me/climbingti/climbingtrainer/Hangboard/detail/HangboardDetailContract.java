package me.climbingti.climbingtrainer.hangboard.detail;

import me.climbingti.climbingtrainer.common.Collection;

/**
 * Created by Aleksi on 30.12.2015.
 * in me.climbingti.climbingtrainer.hangboard.detail
 */
public interface HangboardDetailContract {

    interface View{
        void showHangboardReps(Collection reps);
    }

    interface Presenter{
        void loadHangboardReps(int practiceId);
    }
}
