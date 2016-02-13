package me.climbingti.climbingtrainer.bouldering;

import org.junit.Before;
import org.junit.Test;

import me.climbingti.climbingtrainer.bouldering.ClimbCollection;
import me.climbingti.climbingtrainer.bouldering.ClimbEntity;
import me.climbingti.climbingtrainer.util.GradeConverter;

import static org.junit.Assert.assertEquals;

/**
 * Created by Aleksi on 13.12.2015.
 */
public class ClimbCollectionTest {

    private ClimbCollection climbCollection;


    @Before
    public void initialize(){
        String[] gradeArray = new String[25];
        gradeArray[0] = "1";
        gradeArray[1] = "2";
        gradeArray[2] = "3";
        gradeArray[3] = "4";
        gradeArray[4] = "4+";
        gradeArray[5] = "5";
        gradeArray[6] = "5+";
        gradeArray[7] = "6A";
        gradeArray[8] = "6A+";
        gradeArray[9] = "6B";
        gradeArray[10] = "6B+";
        gradeArray[11] = "6C";
        gradeArray[12] = "6C+";
        gradeArray[13] = "7A";
        gradeArray[14] = "7A+";
        gradeArray[15] = "7B";
        gradeArray[16] = "7B+";
        gradeArray[17] = "7C";
        gradeArray[18] = "7C+";
        gradeArray[19] = "8A";
        gradeArray[20] = "8A+";
        gradeArray[21] = "8B";
        gradeArray[22] = "8B+";
        gradeArray[23] = "8C";
        gradeArray[24] = "8C+";
        GradeConverter.setGradingScale(gradeArray);
        climbCollection = new ClimbCollection();
        climbCollection.add(createEntity(7));
        climbCollection.add(createEntity(7));
        climbCollection.add(createEntity(7));
        climbCollection.add(createEntity(5));
        climbCollection.add(createEntity(10));
        climbCollection.add(createEntity(11));
        climbCollection.add(createEntity(8));
        climbCollection.add(createEntity(4));
    }


    @Test
    public void getAvgGrade_IsCorrect(){
        assertEquals("6A", climbCollection.getAvgGrade());
    }

    @Test
    public void getMaxGrade_IsCorrect(){

        assertEquals("6C", climbCollection.getMaxGrade());
    }

    private ClimbEntity createEntity(int grade){
        ClimbEntity entity = new ClimbEntity();
        entity.setGrade(grade);
        return entity;
    }
}