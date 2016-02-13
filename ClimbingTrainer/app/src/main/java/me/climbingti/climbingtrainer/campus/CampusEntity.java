package me.climbingti.climbingtrainer.campus;

import me.climbingti.climbingtrainer.common.Entity;

/**
 * Created by Pekka Melgin on 22.10.2015.
 */
public class CampusEntity extends Entity {

    private int steps;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }
}