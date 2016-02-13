package me.climbingti.climbingtrainer.bouldering;

import me.climbingti.climbingtrainer.common.Entity;
import me.climbingti.climbingtrainer.util.GradeConverter;

/**
 * Created by Pekka Melgin on 22.10.2015.
 */
public class ClimbEntity extends Entity {

    private String grade;
    private int gradeInt;

    public String getGrade() {
        return grade;
    }

    public int getGradeInt() {
        return gradeInt;
    }

    public void setGrade(String grade) {
        this.grade = grade;
        this.gradeInt = GradeConverter.gradeToInt(grade);
    }

    public void setGrade(int grade) {
        this.gradeInt = grade;
        this.grade = GradeConverter.intToGrade(grade);
    }
}