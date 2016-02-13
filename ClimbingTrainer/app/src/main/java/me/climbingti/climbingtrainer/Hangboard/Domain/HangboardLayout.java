package me.climbingti.climbingtrainer.hangboard.Domain;

import me.climbingti.climbingtrainer.common.Entity;

/**
 * Created by Aleksi on 26.12.2015.
 * in com.example.aleksi.servicetest.circleAnimation
 */
public class HangboardLayout extends Entity {

    private int hangTime;
    private int restTime;
    private int reps;


    public int getHangTime() {
        return hangTime;
    }

    public HangboardLayout setHangTime(int hangTime) {
        this.hangTime = hangTime;
        return this;
    }

    public int getRestTime() {
        return restTime;
    }

    public HangboardLayout setRestTime(int restTime) {
        this.restTime = restTime;
        return this;
    }

    public int getReps() {
        return reps;
    }

    public HangboardLayout setReps(int reps) {
        this.reps = reps;
        return this;
    }

}