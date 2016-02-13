package me.climbingti.climbingtrainer.bouldering;

import me.climbingti.climbingtrainer.common.Collection;
import me.climbingti.climbingtrainer.util.GradeConverter;

/**
 * Created by Aleksi on 10.11.2015.
 */
public class ClimbCollection extends Collection {

    public String getAvgGrade() {
        int totalGrade = 0;
        int gradeCount = 0;

        for (int i = 0; i < list.size(); i++) {
            ClimbEntity entity = (ClimbEntity) list.get(i);
            totalGrade += entity.getGradeInt();
            gradeCount++;
        }

        return GradeConverter.intToGrade((totalGrade / gradeCount));
    }



    public String getMaxGrade() {
        int maxGrade = 0;

        for (int i =0; i< list.size(); i++) {
            ClimbEntity entity = (ClimbEntity) list.get(i);
            if (entity.getGradeInt() > maxGrade) {
                maxGrade = entity.getGradeInt();
            }
        }
        return GradeConverter.intToGrade(maxGrade);
    }
}