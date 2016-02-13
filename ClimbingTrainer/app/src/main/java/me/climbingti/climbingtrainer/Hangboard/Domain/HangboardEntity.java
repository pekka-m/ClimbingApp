package me.climbingti.climbingtrainer.hangboard.Domain;

import me.climbingti.climbingtrainer.common.Entity;

/**
 * Created by Pekka Melgin on 22.10.2015.
 */
public class HangboardEntity extends Entity {

    private int hangTime;

    public int getHangTime() {
        return hangTime;
    }

    public void setHangTime(int hangTime) {
        this.hangTime = hangTime;
    }
}