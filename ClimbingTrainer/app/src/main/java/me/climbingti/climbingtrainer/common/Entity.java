package me.climbingti.climbingtrainer.common;

import java.util.Date;

import me.climbingti.climbingtrainer.util.DateConverter;

/**
 * Created by Pekka Melgin on 4.11.2015.
 */
public class Entity {

    private int id;
    private int practiceId;
    private Date date;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getPracticeId() {
        return practiceId;
    }

    public void setPracticeId(int practiceId) {
        this.practiceId = practiceId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(String date) {
        DateConverter dateConverter = new DateConverter();
        this.date = dateConverter.parseIntoISO8601(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
