package me.climbingti.climbingtrainer.mainview.campus;

import me.climbingti.climbingtrainer.common.SQLiteHelper;
import me.climbingti.climbingtrainer.mainview.bouldering.MainViewBoulderingContract;
import me.climbingti.climbingtrainer.statistics.StatisticsInteractor;

/**
 * Created by Aleksi on 31.12.2015.
 * in me.climbingti.climbingtrainer.mainview.campus
 */
public class MainViewCampusPresenter implements MainViewCampusContract.Presenter {

    private StatisticsInteractor statisticsInteractor;

    private MainViewCampusContract.View view;

    public MainViewCampusPresenter(StatisticsInteractor statisticsInteractor, MainViewCampusContract.View view) {
        this.statisticsInteractor = statisticsInteractor;
        this.view = view;
    }

    @Override
    public void loadCampusReps() {
        try {
            this.view.showCampusReps(statisticsInteractor.getEntitiesGroupedByDay(SQLiteHelper.TABLE_CAMPUS));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}