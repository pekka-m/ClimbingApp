package me.climbingti.climbingtrainer.campus.addcampus;

/**
 * Created by Aleksi on 30.12.2015.
 * in me.climbingti.climbingtrainer.campus
 */
public interface AddCampusContract {

    interface View{
        void showDataBaseError();

        void showRepSaved();
    }

    interface Presenter{

        void addNewCampusRep(String steps, String campusType);

    }
}