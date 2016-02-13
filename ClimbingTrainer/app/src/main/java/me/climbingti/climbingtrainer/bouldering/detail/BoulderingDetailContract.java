package me.climbingti.climbingtrainer.bouldering.detail;

import me.climbingti.climbingtrainer.common.Collection;

/**
 * Created by Aleksi on 30.12.2015.
 * in me.climbingti.climbingtrainer.climb.detail
 */
public interface BoulderingDetailContract {

    interface View{
        void showBoulderingSends(Collection boulderingSends);
    }

    interface Presenter{
        void loadBoulderingSends(int practiceId);
    }
}