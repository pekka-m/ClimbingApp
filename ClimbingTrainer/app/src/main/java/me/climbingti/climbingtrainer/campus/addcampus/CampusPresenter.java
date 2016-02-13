package me.climbingti.climbingtrainer.campus.addcampus;

import android.util.Log;

import me.climbingti.climbingtrainer.campus.CampusInteractor;
import me.climbingti.climbingtrainer.common.SQLiteHelper;
import me.climbingti.climbingtrainer.statistics.StatisticsInteractor;

/**
 * Created by Pekka Melgin on 22.10.2015.
 */
public class CampusPresenter implements AddCampusContract.Presenter {

    private AddCampusContract.View view;
    private CampusInteractor campusInteractor;


    public CampusPresenter( CampusInteractor campusInteractor,AddCampusContract.View view) {
        this.view = view;
        this.campusInteractor = campusInteractor;
    }

    @Override
    public void addNewCampusRep(String steps, String campusType) {
        try {
            this.campusInteractor.addCampus(steps,campusType);
            this.view.showRepSaved();
        }
        catch (Exception e) {
            this.view.showDataBaseError();
        }
    }

//    @Override
//    public void getDataForDetailFragment(int practiceId){
//            this.view.setData(campusInteractor.fetchByPracticeId(practiceId));
//
//    }


    public void getSharedPreferences() {
     //   this.view.onResult("Last inserted steps: " + campusInteractor.getSharedPreferences(this.context));
    }
}