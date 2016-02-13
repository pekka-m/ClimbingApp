package me.climbingti.climbingtrainer.overview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import me.climbingti.climbingtrainer.R;
import me.climbingti.climbingtrainer.common.Injection;
import me.climbingti.climbingtrainer.practice.PracticeOverViewEntity;

/**
 * Created by H3173 on 19.11.2015.
 */
public class OverviewActivity extends AppCompatActivity  implements OverViewContract.View {

    private TextView sends;
    private TextView maxGrade;
    private TextView hangTotal;
    private TextView campusDistance;
    private TextView campusSteps;
    private OverViewContract.Presenter presenter;


    public static Intent getCallingIntent(Context context){
        return new Intent(context, OverviewActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overview_activity);
        setUpTextViews();

        presenter = new OverviewPresenter(Injection.createStatisticsInteractor(this), this);
        presenter.loadAllTimeStats();
    }

    @Override
    public void showAllTimeStats(PracticeOverViewEntity entity) {
        sends.setText(Integer.toString(entity.getNumberofSends()));
        campusDistance.setText(Double.toString(entity.getCampusDistance()));
        campusSteps.setText(Integer.toString(entity.getCampusSteps()));
        maxGrade.setText(entity.getMaxGrade());
        hangTotal.setText(Integer.toString(entity.getTotalHangtime()));
    }

    private void setUpTextViews(){
        sends = (TextView) findViewById(R.id.overview_textView_sendValue);
        campusDistance = (TextView) findViewById(R.id.overview_textView_distanceValue);
        campusSteps = (TextView) findViewById(R.id.overview_textView_stepsValue);
        maxGrade = (TextView) findViewById(R.id.overview_textView_maxGradeValue);
        hangTotal = (TextView) findViewById(R.id.overview_textView_hangboardValue);
    }

}