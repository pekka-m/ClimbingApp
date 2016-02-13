package me.climbingti.climbingtrainer.common;

import android.database.Cursor;

/**
 * Created by Aleksi on 24.10.2015.
 */
public interface Mapper {
    int store(Entity entity) throws Exception;
    Entity fetch(int id) throws Exception;
    Entity fetchLatest() throws  Exception;
    Collection fetchLatest(int numberOfPractices) throws Exception;
    Collection fetchByPracticeId(int practiceId) throws Exception;
    Collection fetchAll() throws Exception;
}
