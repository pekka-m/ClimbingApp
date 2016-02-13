package me.climbingti.climbingtrainer.feed;

import me.climbingti.climbingtrainer.common.Collection;
import me.climbingti.climbingtrainer.common.SharedPreferencesManagerInterface;
import me.climbingti.climbingtrainer.statistics.StatisticsInteractor;

/**
 * Created by Aleksi on 5.11.2015.
 */
public class FeedPresenter implements FeedContract.Presenter{

    private StatisticsInteractor statisticsInteractor;
    private SharedPreferencesManagerInterface manager;
    private FeedContract.View view;

    public FeedPresenter(StatisticsInteractor statisticsInteractor, SharedPreferencesManagerInterface manager, FeedContract.View view) {
        this.statisticsInteractor = statisticsInteractor;
        this.manager = manager;
        this.view = view;
    }

    // TODO: 30.12.2015 change throws Exception into something else like on error etc.
    @Override
    public void loadCards() throws Exception {
        int numberOfPractices = manager.getNumberOfPracticesToShow();
        Collection data = statisticsInteractor.getDataForPracticeCards(numberOfPractices);
        this.view.showCards(data);
    }
}