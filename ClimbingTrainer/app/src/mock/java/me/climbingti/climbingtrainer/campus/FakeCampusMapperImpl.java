package me.climbingti.climbingtrainer.campus;

import me.climbingti.climbingtrainer.common.Collection;
import me.climbingti.climbingtrainer.common.Entity;
import me.climbingti.climbingtrainer.common.Mapper;

/**
 * Created by Aleksi on 30.12.2015.
 * in me.climbingti.climbingtrainer.campus
 */
public class FakeCampusMapperImpl implements Mapper {

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
        Collection entities = new CampusCollection();
        entities.add(returnEntityWithData(2, 1, "2015-12-13 12:38:16"));
        entities.add(returnEntityWithData(2, 2, "2015-12-13 12:38:17"));
        entities.add(returnEntityWithData(2, 3, "2015-12-13 12:38:20"));
        entities.add(returnEntityWithData(2, 2, "2015-12-13 12:38:22"));
        entities.add(returnEntityWithData(2, 3, "2015-12-13 12:38:23"));
        entities.add(returnEntityWithData(2, 4, "2015-12-13 12:38:25"));
        entities.add(returnEntityWithData(2, 5, "2015-12-13 12:3:26"));
        return entities;
    }

    @Override
    public Collection fetchAll() throws Exception {
        Collection entities = new CampusCollection();
        entities.add(returnEntityWithData(0, 1, "2015-12-13 12:36:06"));
        entities.add(returnEntityWithData(0, 2, "2015-12-13 12:36:06"));
        entities.add(returnEntityWithData(0, 3, "2015-12-13 12:36:06"));
        entities.add(returnEntityWithData(1, 2, "2015-12-13 12:37:06"));
        entities.add(returnEntityWithData(1, 3, "2015-12-13 12:37:06"));
        entities.add(returnEntityWithData(1, 4, "2015-12-13 12:37:06"));
        entities.add(returnEntityWithData(2, 5, "2015-12-13 12:38:06"));
        return entities;
    }

    private CampusEntity returnEntityWithData(int practiceId, int steps, String date){
        CampusEntity entity = new CampusEntity();
        entity.setPracticeId(practiceId);
        entity.setSteps(steps);
        entity.setDate(date);
        return entity;
    }
}