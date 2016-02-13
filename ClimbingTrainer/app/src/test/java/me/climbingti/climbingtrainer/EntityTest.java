package me.climbingti.climbingtrainer;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import me.climbingti.climbingtrainer.campus.CampusCollection;
import me.climbingti.climbingtrainer.campus.CampusEntity;
import me.climbingti.climbingtrainer.common.Entity;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

/**
 * Created by Aleksi on 13.12.2015.
 */
public class EntityTest {

    @Test
    public void getDate_isCorrcet(){
        Entity entity = new Entity();
        entity.setDate("2015-12-13 12:36:06");
        assertThat(entity.getDate(), instanceOf(Date.class));
        assertEquals("Sun Dec 13 12:36:06 EET 2015", entity.getDate().toString());
    }
}