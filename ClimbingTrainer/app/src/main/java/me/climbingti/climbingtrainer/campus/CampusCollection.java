package me.climbingti.climbingtrainer.campus;


/**
 * Created by Aleksi on 10.11.2015.
 * in ${PACKAGE_NAME}
 */
public class CampusCollection extends me.climbingti.climbingtrainer.common.Collection {


    public int getTotalSteps() {
        int totalSteps = 0;
        for (int i = 0; i < list.size(); i++) {
            CampusEntity entity = (CampusEntity) list.get(i);
            totalSteps += entity.getSteps();
        }
        return totalSteps;
    }


    public double getDistanceCampused() {
        return 0;
    }
}