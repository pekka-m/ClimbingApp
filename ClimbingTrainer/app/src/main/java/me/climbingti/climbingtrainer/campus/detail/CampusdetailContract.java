package me.climbingti.climbingtrainer.campus.detail;

import me.climbingti.climbingtrainer.common.Collection;

/**
 * Created by Aleksi on 30.12.2015.
 * in me.climbingti.climbingtrainer.campus.detail
 */
public interface CampusdetailContract {

    interface View{
        void showCampusReps(Collection campusReps);
    }

    interface Presenter{
        void loadCampusReps(int practiceId);
    }
}