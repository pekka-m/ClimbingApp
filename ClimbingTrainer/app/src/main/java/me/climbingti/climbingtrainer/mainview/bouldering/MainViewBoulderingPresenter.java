package me.climbingti.climbingtrainer.mainview.bouldering;

import me.climbingti.climbingtrainer.common.SQLiteHelper;
import me.climbingti.climbingtrainer.statistics.StatisticsInteractor;

/**
 * Created by Aleksi on 31.12.2015.
 * in me.climbingti.climbingtrainer.mainview.bouldering
 */
public class MainViewBoulderingPresenter implements MainViewBoulderingContract.Presenter {


    private StatisticsInteractor statisticsInteractor;

    private MainViewBoulderingContract.View view;

    public MainViewBoulderingPresenter(StatisticsInteractor statisticsInteractor, MainViewBoulderingContract.View view) {
        this.statisticsInteractor = statisticsInteractor;
        this.view = view;
    }

    @Override

    public void loadBoulderingSends() {
        try {
            this.view.showBoulderingSends(statisticsInteractor.getEntitiesGroupedByDay(SQLiteHelper.TABLE_CLIMB));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}