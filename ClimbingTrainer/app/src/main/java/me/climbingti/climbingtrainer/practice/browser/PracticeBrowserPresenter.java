package me.climbingti.climbingtrainer.practice.browser;

import me.climbingti.climbingtrainer.common.Collection;
import me.climbingti.climbingtrainer.statistics.StatisticsInteractor;

/**
 * Created by Aleksi on 7.12.2015.
 */
public class PracticeBrowserPresenter implements PracticeBrowserContract.Presenter{

    private StatisticsInteractor statisticsInteractor;
    private PracticeBrowserContract.View view;

    public PracticeBrowserPresenter(StatisticsInteractor statisticsInteractor, PracticeBrowserContract.View view) {
        this.statisticsInteractor = statisticsInteractor;
        this.view = view;
    }

    @Override
    public void loadAllPracticeSessions()  {
        Collection data = null;
        try {
            data = statisticsInteractor.getDataForPracticeCards();
            this.view.showAllPracticeSessions(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}