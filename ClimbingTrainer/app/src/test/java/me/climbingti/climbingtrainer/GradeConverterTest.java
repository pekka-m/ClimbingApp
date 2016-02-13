package me.climbingti.climbingtrainer;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import me.climbingti.climbingtrainer.util.GradeConverter;


/**
 * Created by Aleksi on 12.12.2015.
 */
public class GradeConverterTest {

    private String[] gradeArray;

    @Before
    public void initialize(){
        gradeArray = new String[25];
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
    }

    @Test
    public void gradeToInt_isCorrect(){
        assertEquals(17, GradeConverter.gradeToInt("7C"));
        assertEquals(23, GradeConverter.gradeToInt("8C"));
        assertEquals(8, GradeConverter.gradeToInt("6A+"));
    }

    @Test
    public void intToGrade_isCorrect(){
        assertEquals("7C", GradeConverter.intToGrade(17));
        assertEquals("8C", GradeConverter.intToGrade(23));
        assertEquals("6A+", GradeConverter.intToGrade(8));
    }






}