package me.climbingti.climbingtrainer.common;

import java.util.Date;

/**
 * Created by Aleksi on 30.12.2015.
 * in me.climbingti.climbingtrainer.common
 */
public interface PracticeMapper extends Mapper {

     void updatePracticeLatestExerciseDateTime(Date date) throws Exception;
}
