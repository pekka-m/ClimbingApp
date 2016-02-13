package me.climbingti.climbingtrainer.common;

/**
 * Created by Aleksi on 2.1.2016.
 * in me.climbingti.climbingtrainer.common
 */
public interface SharedPreferencesManagerInterface {
    int getGrading();

    int getCampusSpacing();

    int getNumberOfPracticesToShow();

    //0 = font, 1 = hueco
    void setGrading(int GradeScale);

    void setCampusSpacing(int spacing);

    void setNumberOfPracticesToShow(int numberOfPracticesToShow);
}
