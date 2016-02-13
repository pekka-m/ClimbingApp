package me.climbingti.climbingtrainer.hangboard.detail;

import me.climbingti.climbingtrainer.hangboard.Domain.HangboardInteractor;

/**
 * Created by Aleksi on 30.12.2015.
 * in me.climbingti.climbingtrainer.hangboard.detail
 */
public class HangboardDetailPresenter implements HangboardDetailContract.Presenter {


    private HangboardInteractor hangboardInteractor;

    private HangboardDetailContract.View view;

    public HangboardDetailPresenter(HangboardInteractor hangboardInteractor, HangboardDetailContract.View view) {
        this.hangboardInteractor = hangboardInteractor;
        this.view = view;
    }

    @Override

    public void loadHangboardReps(int practiceId) {
        this.view.showHangboardReps(hangboardInteractor.fetchByPracticeId(practiceId));
    }
}