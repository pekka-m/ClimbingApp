package me.climbingti.climbingtrainer.practice;

import android.util.Log;

import java.util.Date;

import me.climbingti.climbingtrainer.util.DateConverter;
import me.climbingti.climbingtrainer.common.Entity;

/**
 * Created by Aleksi on 26.11.2015.
 */
public class PracticeEntity extends Entity {
    private Date endTime;

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String date) {
        Log.d("PracticeEntity", "setEndTime: date param: " + date);
        DateConverter dateConverter = new DateConverter();
        this.endTime = dateConverter.parseIntoISO8601(date);
    }

    public void setEndTime(Date date){
        this.endTime = date;
    }
}