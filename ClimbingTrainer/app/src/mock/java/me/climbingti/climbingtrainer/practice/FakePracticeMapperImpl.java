package me.climbingti.climbingtrainer.practice;

import java.util.Date;

import me.climbingti.climbingtrainer.common.Collection;
import me.climbingti.climbingtrainer.common.Entity;
import me.climbingti.climbingtrainer.common.Mapper;
import me.climbingti.climbingtrainer.common.PracticeMapper;

/**
 * Created by Aleksi on 30.12.2015.
 * in me.climbingti.climbingtrainer.practice
 */
public class FakePracticeMapperImpl implements PracticeMapper {

    @Override
    public void updatePracticeLatestExerciseDateTime(Date date) throws Exception {

    }

    /*
        fetchLatest
        fetchAll
        teevirhe

         */
    @Override
    public int store(Entity entity) throws Exception {
        return 1;

    }

    @Override
    public Entity fetch(int id) throws Exception {
        PracticeEntity practice = new PracticeEntity();
        switch (id){
            case 0:
                practice.setPracticeId(id);
                practice.setDate("2015-12-13 12:36:06");
                practice.setEndTime("2015-12-13 12:36:26");
                break;
            case 1:
                practice.setPracticeId(id);
                practice.setDate("2015-12-13 12:37:06");
                practice.setEndTime("2015-12-13 12:37:26");
                break;
            case 2:
                practice.setPracticeId(id);
                practice.setDate("2015-12-13 12:38:06");
                practice.setEndTime("2015-12-13 12:38:26");
                break;
            default:
                break;
        }
        return practice;
    }

    @Override
    public Entity fetchLatest() throws Exception {
        return returnWithData(1, "2015-12-13 12:37:06", "2015-12-13 12:37:26");
    }


    // numberofPractices is 2
    @Override
    public Collection fetchLatest(int numberOfPractices) throws Exception {
        Collection collection = new Collection();
        collection.add(returnWithData(1, "2015-12-13 12:37:06", "2015-12-13 12:37:26"));
        collection.add(returnWithData(2, "2015-12-13 12:38:06","2015-12-13 12:38:26"));
        return collection;
    }

    @Override
    public Collection fetchByPracticeId(int practiceId) throws Exception {
        return null;
    }

    @Override
    public Collection fetchAll() throws Exception {
        Collection collection = new Collection();
        collection.add(returnWithData(0, "2015-12-13 12:36:06", "2015-12-13 12:36:26"));
        collection.add(returnWithData(1, "2015-12-13 12:37:06", "2015-12-13 12:37:26"));
        collection.add(returnWithData(2, "2015-12-13 12:38:06","2015-12-13 12:38:26"));
        return collection;
    }

    private PracticeEntity returnWithData(int id, String date, String endTime){
        PracticeEntity practice = new PracticeEntity();
        practice.setPracticeId(id);
        practice.setDate(date);
        practice.setEndTime(endTime);
        return practice;
    }
}
