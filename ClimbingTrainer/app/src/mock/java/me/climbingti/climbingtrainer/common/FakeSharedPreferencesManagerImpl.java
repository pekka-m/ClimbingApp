package me.climbingti.climbingtrainer.common;

/**
 * Created by Aleksi on 2.1.2016.
 * in me.climbingti.climbingtrainer.common
 */
public class FakeSharedPreferencesManagerImpl implements SharedPreferencesManagerInterface{

    public FakeSharedPreferencesManagerImpl()  {
    }

    public int getGrading() {
        return 2131492866;
    }

    public int getCampusSpacing() {
        return 0;
    }

    public int getNumberOfPracticesToShow(){
        return 3;
    }
    //0 = font, 1 = hueco
    public void setGrading(int GradeScale) {

    }

    public void setCampusSpacing(int spacing) {

    }

    public void setNumberOfPracticesToShow(int numberOfPracticesToShow){

    }
}
