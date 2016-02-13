package me.climbingti.climbingtrainer.overview;

import me.climbingti.climbingtrainer.practice.PracticeOverViewEntity;

/**
 * Created by Aleksi on 1.1.2016.
 * in me.climbingti.climbingtrainer.overview
 */
public interface OverViewContract {

    interface View {
     void   showAllTimeStats(PracticeOverViewEntity entity);
    }

    interface Presenter{
        // TODO: 1.1.2016 rename better
        void loadAllTimeStats();
    }
}
