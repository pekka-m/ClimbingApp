package me.climbingti.climbingtrainer.practice;

import me.climbingti.climbingtrainer.common.Entity;

/**
 * Created by Aleksi on 5.11.2015.
 */
public class PracticeOverViewEntity extends Entity {
    private int numberofSends;
    private String maxGrade;
    private String[] projectingGrades;
    private int totalHangtime;
    private int campusSteps;
    private double campusDistance;

    public int getNumberofSends() {
        return numberofSends;
    }

    public void setNumberofSends(int numberofSends) {
        this.numberofSends = numberofSends;
    }

    public String getMaxGrade() {
        return maxGrade;
    }

    public void setMaxGrade(String maxGrade) {
        this.maxGrade = maxGrade;
    }

    public String[] getProjectingGrades() {
        return projectingGrades;
    }

    public void setProjectingGrades(String[] projectingGrades) {
        this.projectingGrades = projectingGrades;
    }

    public int getTotalHangtime() {
        return totalHangtime;
    }

    public void setTotalHangtime(int totalHangtime) {
        this.totalHangtime = totalHangtime;
    }

    public int getCampusSteps() {
        return campusSteps;
    }

    public void setCampusSteps(int campusSteps) {
        this.campusSteps = campusSteps;
    }

    public double getCampusDistance() {
        return campusDistance;
    }

    public void setCampusDistance(double campusDistance) {
        this.campusDistance = campusDistance;
    }
}