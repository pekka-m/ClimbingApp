package me.climbingti.climbingtrainer.campus.detail;

import me.climbingti.climbingtrainer.campus.CampusInteractor;
import me.climbingti.climbingtrainer.common.Collection;
import me.climbingti.climbingtrainer.common.exception.TestException;

/**
 * Created by Aleksi on 30.12.2015.
 * in me.climbingti.climbingtrainer.campus.detail
 */
public class CampusDetailPresenter implements CampusdetailContract.Presenter {

    private CampusInteractor campusInteractor;
    private CampusdetailContract.View view;

    public CampusDetailPresenter(CampusInteractor campusInteractor, CampusdetailContract.View view) {
        this.campusInteractor = campusInteractor;
        this.view = view;
    }

    @Override
    public void loadCampusReps(int practiceId) {
        Collection collection = campusInteractor.fetchByPracticeId(practiceId);
        this.view.showCampusReps(collection);
    }

}