package me.climbingti.climbingtrainer.bouldering;

import android.content.Context;
import android.util.Log;

import me.climbingti.climbingtrainer.common.Injection;
import me.climbingti.climbingtrainer.common.SQLiteHelper;
import me.climbingti.climbingtrainer.common.SharedPreferencesManager;
import me.climbingti.climbingtrainer.common.SharedPreferencesManagerInterface;
import me.climbingti.climbingtrainer.statistics.StatisticsInteractor;
import me.climbingti.climbingtrainer.util.GradeConverter;

/**
 * Created by Pekka Melgin on 22.10.2015.
 */
public class ClimbPresenter{

    private ClimbView view;
    private ClimbInteractor climbInteractor;
    private StatisticsInteractor statisticsInteractor;
    private Context context;
    private SharedPreferencesManagerInterface prefManager;

    public ClimbPresenter(ClimbView activity) {
        this.view = activity;
        this.context = (Context) view;
        climbInteractor = Injection.createClimbInteractor(context);
        statisticsInteractor = Injection.createStatisticsInteractor(context);

        //setting up the grading scale
        prefManager = new SharedPreferencesManager(context);
        String[] gradingScale = context.getResources().getStringArray(prefManager.getGrading());
        GradeConverter.setGradingScale(gradingScale);
    }
    public ClimbPresenter(ClimbView activity, Context context) {
        this.view = activity;
        this.context = context;
        climbInteractor = Injection.createClimbInteractor(context);
        statisticsInteractor = Injection.createStatisticsInteractor(context);

        //setting up the grading scale
        prefManager = new SharedPreferencesManager(context);
        String[] gradingScale = context.getResources().getStringArray(prefManager.getGrading());
        GradeConverter.setGradingScale(gradingScale);
    }





    public void add(int grade) {
        try {
            Log.d("ClimbPresenter", "add: grade value: " + grade);
            this.climbInteractor.addClimb(grade);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void getData() {
        try {
            this.view.setData(statisticsInteractor.getEntitiesGroupedByDay(SQLiteHelper.TABLE_CLIMB));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getDataForDetailFragment(int practiceId){
        this.view.setData(climbInteractor.fetchByPracticeId(practiceId));
    }

    public int getGradingScale(){
      return prefManager.getGrading();
    }
}
