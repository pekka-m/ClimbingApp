package me.climbingti.climbingtrainer.practice;

import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import me.climbingti.climbingtrainer.common.Mapper;
import me.climbingti.climbingtrainer.common.PracticeMapper;

/**
 * Created by Pekka Melgin on 5.11.2015.
 */
public class PracticeInteractor {
    private PracticeMapper practiceSQLiteMapper;

    public PracticeInteractor(PracticeMapper practiceSQLiteMapper) {
        this.practiceSQLiteMapper =  practiceSQLiteMapper;
    }


    /*
    kun tehdään uus practice niin ei storeteta nullia vaan semmone missä on nykyne aika end timena

    getPracticeId();
    1.get practice id should first take current date into a variable
    2.then it should get the latest practiceId thingy and then get the latest exercise end time
    3.and then compare if the current date time is more than 30 mins from the latest updated exercise end time.
    4.if the current time is > 30 mins from endtime then this method should return new practice id.
    5.if the current time is < 30 mins from endtime then ths method should update the endtime of current practiceid.
     */

    public int getPracticeId() throws Exception {
        Date currentDateTime= new Date();

        DateFormat format = new SimpleDateFormat("yyyyMMddHHmm", Locale.getDefault());

        long currentDateTimeLong = Long.parseLong(format.format(currentDateTime));

        PracticeEntity practiceEntity = (PracticeEntity) practiceSQLiteMapper.fetchLatest();

        long latestPracticeDateTime = 0;

        try {
            Date endTime = practiceEntity.getEndTime();
            String endTimeString = format.format(endTime);
            latestPracticeDateTime = Long.parseLong(endTimeString);
        } catch (NumberFormatException e) {
            Log.d("PracticeInteractor", "number format exception");
            Log.d("PracticeInteractor", "latestPracticeDateTime value: " + latestPracticeDateTime);
        }
        catch (NullPointerException e){
            Log.d("PracticeInteractor", "null pointer exception");
            Log.d("PracticeInteractor", "latestPracticeDateTime value: " + latestPracticeDateTime);
        }
        // 3
        if (currentDateTimeLong > latestPracticeDateTime) {
            // 4
            // use the same object instead of creating new one, just changing the endtime
            // with current datetime
            // store returns the current id of the row
            practiceEntity.setEndTime(currentDateTime);
            return practiceSQLiteMapper.store(practiceEntity);
        }
        else {
            //5
            practiceSQLiteMapper.updatePracticeLatestExerciseDateTime(currentDateTime);
            return practiceEntity.getPracticeId();
        }
    }
}