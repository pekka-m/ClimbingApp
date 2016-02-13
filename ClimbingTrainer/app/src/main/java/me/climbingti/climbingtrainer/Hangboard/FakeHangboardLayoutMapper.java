package me.climbingti.climbingtrainer.hangboard;

import me.climbingti.climbingtrainer.hangboard.Domain.HangboardLayout;
import me.climbingti.climbingtrainer.common.Collection;
import me.climbingti.climbingtrainer.common.Entity;
import me.climbingti.climbingtrainer.common.Mapper;

/**
 * Created by Aleksi on 28.12.2015.
 * in me.climbingti.climbingtrainer.Hangboard.DataAccess
 */
public class FakeHangboardLayoutMapper implements Mapper {

    @Override
    public int store(Entity entity) throws Exception {
        return 0;
    }

    @Override
    public Entity fetch(int id) throws Exception {
        return null;
    }

    @Override
    public Entity fetchLatest() throws Exception {
        return null;
    }

    @Override
    public Collection fetchLatest(int numberOfPractices) throws Exception {
        return null;
    }

    @Override
    public Collection fetchByPracticeId(int practiceId) throws Exception {
        return null;
    }

    @Override
    public Collection fetchAll() throws Exception {
        Collection collection = new Collection();
        HangboardLayout[] temp = new HangboardLayout[3];
        collection.add(new HangboardLayout().setHangTime(7).setRestTime(3).setReps(6));
        collection.add(new HangboardLayout().setHangTime(10).setRestTime(60).setReps(5));
        collection.add(new HangboardLayout().setHangTime(5).setRestTime(5).setReps(3));
        return collection;
    }
}