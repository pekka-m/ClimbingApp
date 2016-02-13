package me.climbingti.climbingtrainer.hangboard;

import me.climbingti.climbingtrainer.common.Collection;
import me.climbingti.climbingtrainer.common.Entity;
import me.climbingti.climbingtrainer.common.Mapper;
import me.climbingti.climbingtrainer.hangboard.Domain.HangboardCollection;
import me.climbingti.climbingtrainer.hangboard.Domain.HangboardEntity;

/**
 * Created by Aleksi on 30.12.2015.
 * in me.climbingti.climbingtrainer.hangboard
 */
public class FakeHangboardMapperImpl implements Mapper {
    @Override
    public int store(Entity entity) throws Exception {
        return 1;
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
        Collection entities = new HangboardCollection();
        entities.add(returnEntityWithData(2, 1, "2015-12-13 12:38:06"));
        entities.add(returnEntityWithData(2, 2, "2015-12-13 12:38:06"));
        return entities;
    }

    @Override
    public Collection fetchAll() throws Exception {
        Collection entities = new HangboardCollection();
        entities.add(returnEntityWithData(0, 1, "2015-12-13 12:36:06"));
        entities.add(returnEntityWithData(0, 2, "2015-12-13 12:36:06"));
        entities.add(returnEntityWithData(0, 3, "2015-12-13 12:36:06"));
        entities.add(returnEntityWithData(1, 2, "2015-12-13 12:37:06"));
        entities.add(returnEntityWithData(1, 3, "2015-12-13 12:37:06"));
        entities.add(returnEntityWithData(1, 4, "2015-12-13 12:37:06"));
        entities.add(returnEntityWithData(2, 5, "2015-12-13 12:38:06"));
        return entities;
    }

    private HangboardEntity returnEntityWithData(int practiceId, int time, String date){
        HangboardEntity entity = new HangboardEntity();
        entity.setPracticeId(practiceId);
        entity.setHangTime(time);
        entity.setDate(date);
        return entity;
    }
}
