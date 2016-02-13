package me.climbingti.climbingtrainer.mainview.hanbgoarding;

import me.climbingti.climbingtrainer.common.SQLiteHelper;
import me.climbingti.climbingtrainer.statistics.StatisticsInteractor;

/**
 * Created by Aleksi on 31.12.2015.
 * in me.climbingti.climbingtrainer.mainview.hanbgoarding
 */
public class MainviewHangboardPresenter implements MainViewHangboardContract.Presenter{


    private StatisticsInteractor statisticsInteractor;
    private MainViewHangboardContract.View view;

    public MainviewHangboardPresenter(StatisticsInteractor statisticsInteractor, MainViewHangboardContract.View view) {
        this.statisticsInteractor = statisticsInteractor;
        this.view = view;
    }

    @Override
    public void loadHangboardReps() {
        try {
            this.view.showHangboardReps(statisticsInteractor.getEntitiesGroupedByDay(SQLiteHelper.TABLE_HANGBOARD));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
