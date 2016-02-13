package me.climbingti.climbingtrainer.hangboard.domain;

import org.junit.Test;

import org.junit.Before;

import me.climbingti.climbingtrainer.hangboard.Domain.HangboardCollection;
import me.climbingti.climbingtrainer.hangboard.Domain.HangboardEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

/**
 * Created by Aleksi on 2.1.2016.
 * in me.climbingti.climbingtrainer.hangboard.domain
 */
public class HangboardCollectionSpec {

    private HangboardCollection hangboardCollection;

    @Before
    public void setUpHangboardCollection(){
        hangboardCollection = new HangboardCollection();
    }

    @Test
    public void itShouldReturnTotalHangTimeOfCurrentReps(){

        int expected;
        int actual;

        HangboardEntity entity = new HangboardEntity();
        entity.setHangTime(5);
        hangboardCollection.add(entity);
        entity = new HangboardEntity();
        entity.setHangTime(7);
        hangboardCollection.add(entity);
        entity = new HangboardEntity();
        entity.setHangTime(2);
        hangboardCollection.add(entity);
        entity = new HangboardEntity();
        entity.setHangTime(758);
        hangboardCollection.add(entity);
        entity = new HangboardEntity();
        entity.setHangTime(263);
        hangboardCollection.add(entity);
        actual = hangboardCollection.getTotalHangTime();
        expected = 1035;

        assertEquals("should return 1035", expected, actual);
    }
}