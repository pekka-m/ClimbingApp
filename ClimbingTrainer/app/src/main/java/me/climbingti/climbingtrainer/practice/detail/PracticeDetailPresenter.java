package me.climbingti.climbingtrainer.practice.detail;

import me.climbingti.climbingtrainer.statistics.StatisticsInteractor;

/**
 * Created by H4198 on 17.11.2015.
 */
public class PracticeDetailPresenter implements PracticeDetailContract.Presenter{
    private StatisticsInteractor statisticsInteractor;
    private PracticeDetailContract.View view;

    public PracticeDetailPresenter(StatisticsInteractor statisticsInteractor, PracticeDetailContract.View view) {
        this.statisticsInteractor = statisticsInteractor;
        this.view = view;
    }

    @Override
    public void loadPracticeDetails(int practiceId) {
        try {
            this.view.showPracticeDetails(statisticsInteractor.getDataForPracticeDetail(practiceId));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}