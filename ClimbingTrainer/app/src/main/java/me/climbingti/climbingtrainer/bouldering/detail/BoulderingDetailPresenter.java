package me.climbingti.climbingtrainer.bouldering.detail;

import me.climbingti.climbingtrainer.bouldering.ClimbInteractor;
import me.climbingti.climbingtrainer.common.Collection;

/**
 * Created by Aleksi on 30.12.2015.
 * in me.climbingti.climbingtrainer.climb.detail
 */
public class BoulderingDetailPresenter implements BoulderingDetailContract.Presenter {

    private ClimbInteractor climbInteractor;
    private BoulderingDetailContract.View view;

    public BoulderingDetailPresenter(ClimbInteractor climbInteractor, BoulderingDetailContract.View view) {
        this.climbInteractor = climbInteractor;
        this.view = view;
    }

    @Override
    public void loadBoulderingSends(int practiceId) {
        Collection collection = climbInteractor.fetchByPracticeId(practiceId);
        this.view.showBoulderingSends(collection);
    }
}