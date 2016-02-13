package me.climbingti.climbingtrainer;


import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.fail;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

import me.climbingti.climbingtrainer.statistics.StatisticsInteractor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by Aleksi on 13.12.2015.
 */
public class StatisticsInteractorTest {

    private StatisticsInteractor statisticsInteractor;
    private String[] gradeArray;

    @Before
    public void initialize(){
//        statisticsInteractor = Injection.createStatisticsInteractor();
//        gradeArray = new String[25];
//        gradeArray[0] = "1";
//        gradeArray[1] = "2";
//        gradeArray[2] = "3";
//        gradeArray[3] = "4";
//        gradeArray[4] = "4+";
//        gradeArray[5] = "5";
//        gradeArray[6] = "5+";
//        gradeArray[7] = "6A";
//        gradeArray[8] = "6A+";
//        gradeArray[9] = "6B";
//        gradeArray[10] = "6B+";
//        gradeArray[11] = "6C";
//        gradeArray[12] = "6C+";
//        gradeArray[13] = "7A";
//        gradeArray[14] = "7A+";
//        gradeArray[15] = "7B";
//        gradeArray[16] = "7B+";
//        gradeArray[17] = "7C";
//        gradeArray[18] = "7C+";
//        gradeArray[19] = "8A";
//        gradeArray[20] = "8A+";
//        gradeArray[21] = "8B";
//        gradeArray[22] = "8B+";
//        gradeArray[23] = "8C";
//        gradeArray[24] = "8C+";
//        GradeConverter.setGradingScale(gradeArray);
    }



    @Test
    public void getEntitiesGroupedByDay_IsCorrect() throws Exception {
        fail("need to think about injection in this test class");
//        ArrayMap<Date, Collection> collectionArrayMap = statisticsInteractor.getEntitiesGroupedByDay(SQLiteHelper.TABLE_CLIMB);
//        assertThat(collectionArrayMap.valueAt(0), instanceOf(ClimbCollection.class));
//        // date is different
//        assertNotEquals(collectionArrayMap.keyAt(0), collectionArrayMap.keyAt(1));
    }

    @Test
    public void getAllTimeStats_IsCorrect() throws Exception {
        fail("need to think about injection in this test class");

//        PracticeOverViewEntity practiceOverViewEntity = statisticsInteractor.getAllTimeStats();
//        assertEquals(7, practiceOverViewEntity.getNumberofSends());
//        assertEquals("5", practiceOverViewEntity.getMaxGrade());
//        assertEquals(20,practiceOverViewEntity.getTotalHangtime());
//        assertEquals(20, practiceOverViewEntity.getCampusSteps());
    }

    @Test
    public void getDataForPracticeDetail_isCorrect() throws  Exception{
        fail("need to think about injection in this test class");

//        PracticeOverViewEntity practiceOverViewEntity = statisticsInteractor.getDataForPracticeDetail(2);
//        assertEquals(7, practiceOverViewEntity.getNumberofSends());
//        assertEquals("5", practiceOverViewEntity.getMaxGrade());
//        assertEquals(3,practiceOverViewEntity.getTotalHangtime());
//        assertEquals(20, practiceOverViewEntity.getCampusSteps());
    }

}