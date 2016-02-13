package me.climbingti.climbingtrainer.overview;

import me.climbingti.climbingtrainer.statistics.StatisticsInteractor;

/**
 * Created by H3173 on 19.11.2015.
 */
public class OverviewPresenter implements OverViewContract.Presenter {
    private StatisticsInteractor statisticsInteractor;
    private OverViewContract.View view;


    public OverviewPresenter(StatisticsInteractor statisticsInteractor, OverViewContract.View view) {
        this.statisticsInteractor = statisticsInteractor;
        this.view = view;
    }

    @Override
    public void loadAllTimeStats() {
        try {
            view.showAllTimeStats(statisticsInteractor.getAllTimeStats());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}