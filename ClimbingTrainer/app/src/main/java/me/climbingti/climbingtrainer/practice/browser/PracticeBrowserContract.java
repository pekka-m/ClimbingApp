package me.climbingti.climbingtrainer.practice.browser;

import me.climbingti.climbingtrainer.common.Collection;

/**
 * Created by Aleksi on 1.1.2016.
 * in me.climbingti.climbingtrainer.practice.browser
 */
public interface PracticeBrowserContract {

    interface View{
        void showAllPracticeSessions(Collection practiceSessions);
    }

    interface Presenter {
        void loadAllPracticeSessions();
    }

}