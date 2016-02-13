package me.climbingti.climbingtrainer.practice.detail;

import me.climbingti.climbingtrainer.practice.PracticeOverViewEntity;

/**
 * Created by Aleksi on 1.1.2016.
 * in me.climbingti.climbingtrainer.practice.detail
 */
public interface PracticeDetailContract {

    interface View {
        void showPracticeDetails(PracticeOverViewEntity practiceDetails);
    }

    interface Presenter {
        void loadPracticeDetails(int practiceId);
    }
}