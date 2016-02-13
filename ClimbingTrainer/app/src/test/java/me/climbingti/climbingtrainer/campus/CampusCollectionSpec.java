package me.climbingti.climbingtrainer.campus;


import org.junit.Before;
import org.junit.Test;

import me.climbingti.climbingtrainer.campus.CampusCollection;
import me.climbingti.climbingtrainer.campus.CampusEntity;

import static org.junit.Assert.assertEquals;

/**
 * Created by Aleksi on 12.12.2015.
 */
public class CampusCollectionSpec {

    private CampusCollection testCollection;

    @Before
    public void initialize(){
        this.testCollection = new CampusCollection();
        }

    /**
     * Tests CampusCollection getTotalSteps method
     * @throws Exception
     */
    @Test
    public void itShouldReturnCorrectAmountOfTotalSteps() throws Exception{
        CampusEntity entity = new CampusEntity();
        entity.setSteps(20);
        testCollection.add(entity);
        CampusEntity entity1 = new CampusEntity();
        entity1.setSteps(40);
        testCollection.add(entity1);
        assertEquals(60, testCollection.getTotalSteps());
    }

    @Test
    public void getSteps_emptyCollection() throws Exception{
        assertEquals(0, testCollection.getTotalSteps());

    }
}