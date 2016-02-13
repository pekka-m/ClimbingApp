package me.climbingti.climbingtrainer.hangboard.addhangboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import me.climbingti.climbingtrainer.R;
import me.climbingti.climbingtrainer.common.Injection;
import me.climbingti.climbingtrainer.common.view.CircleClockView;
import me.climbingti.climbingtrainer.hangboard.FakeHangboardLayoutMapper;


/**
 * Created by Pekka Melgin on 23.10.2015.
 */
public class HangboardActivity extends AppCompatActivity implements AddHangboardContract.View{

    private Button buttonStart;
    private TextView textView_timer;
    private AddHangboardContract.Presenter addHangboardPresenter;

    private CircleClockView circleClockView;
    private int tickCounter;
    private int numberOfReps;
    private boolean isRestTime;
    private int restDuration;
    private int hangDuration;
    private ViewPager viewPager;
    private FragmentStatePagerAdapter tempAdapter;
    private HangboardLayoutViewPagerLayoutFragment fragment;
    private RelativeLayout rootView;

    public static Intent getCallingIntent(Context context){
        return new Intent(context, HangboardActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hangboard_activity);

        addHangboardPresenter = new AddHangboardPresenter(Injection.createHangboardInteractor(this),
                Injection.createStatisticsInteractor(this),
                new FakeHangboardLayoutMapper(), this);

        initViews();
        initViewPager();
    }

    private void initViews(){
        buttonStart = (Button) findViewById(R.id.hangboardActivity_button_addHang);
        circleClockView = (CircleClockView) findViewById(R.id.hangboardActivity_CIrcleClockView);
        rootView = (RelativeLayout) findViewById(R.id.hangboardActivity_RelativeLayout);
        viewPager = (ViewPager) findViewById(R.id.hangboardActivity_viewPager);


        //setting listeners
        buttonStart.setOnClickListener(addHang());
        circleClockView.setOnCycleFinishListener(listener());
    }


    @Override
    public void showHangboardRepAdded() {
        Snackbar
                .make(rootView, "hangboard session added", Snackbar.LENGTH_SHORT)
                .setAction("action", null)
                .show();
    }

    @Override
    public void showHangboardingSessions() {

    }

    @Override
    public void showDatabaseError() {

    }

    private void doStart(int  integer) {
        // mStartTime = time;
        circleClockView.reset();
        circleClockView.setmAccentColor(ContextCompat.getColor(this,R.color.timerColor));
        circleClockView.setIntervalTime(integer * DateUtils.SECOND_IN_MILLIS);
        circleClockView.startIntervalAnimation();
        isRestTime = true;
        tickCounter++;
    }

    private void doStartRestTimer(int restTime){
        circleClockView.reset();
        circleClockView.setmAccentColor(ContextCompat.getColor(this,R.color.timerRest));
        circleClockView.setIntervalTime(restTime*DateUtils.SECOND_IN_MILLIS);
        circleClockView.startIntervalAnimation();
        isRestTime = false;

    }

    private void doStop(){
        circleClockView.stopIntervalAnimation();
        // vai
        //molemmatko?
        circleClockView.reset();
    }
    private void initViewPager(){
        HangboardCardLayoutPagerAdapter adapter = null;
        try {
            adapter = new HangboardCardLayoutPagerAdapter(getSupportFragmentManager(), addHangboardPresenter.loadHangboardingSessions());
        } catch (Exception e) {
            e.printStackTrace();
        }
        viewPager.setAdapter(adapter);
        tempAdapter = (FragmentStatePagerAdapter) viewPager.getAdapter();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.i("INFO", "onPageSelected: position: " + position);
                fragment = (HangboardLayoutViewPagerLayoutFragment) tempAdapter.instantiateItem(viewPager, position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //instantiate fragment as the first fragment in order to avoid crash
        fragment = (HangboardLayoutViewPagerLayoutFragment) tempAdapter.instantiateItem(viewPager, 0);
    }


    private CircleClockView.OnCycleFinishListener listener() {
        return new CircleClockView.OnCycleFinishListener() {
            @Override
            public void onCycleFinish() {

                if(tickCounter == numberOfReps){
                    doStop();
                    tickCounter = 0;
                }
                else if (isRestTime){
                    doStartRestTimer(restDuration);
                }
                else {
                    doStart(hangDuration);
                }

            }
        };
    }

    private View.OnClickListener addHang() {
        return new View.OnClickListener() {
            public void onClick(View v) {
                numberOfReps = fragment.getData().getReps();
                restDuration = fragment.getData().getRestTime();
                hangDuration = fragment.getData().getHangTime();
                doStartRestTimer(5);

//                String[] params = {
//                        editText_hangboardTime.getText().toString(),
//                        editText_hangboardRestTime.getText().toString(),
//                        editText_hangboardReps.getText().toString()
//                };
//
//                new HangboardTask().execute(params);

                /*
                addHangboardPresenter.addHang(
                        editText_hangboardTime.getText().toString(),
                        editText_hangboardRestTime.getText().toString(),
                        editText_hangboardReps.getText().toString()
                );
                */
            }
        };
    }



    /*
    deprecated
    could be use of later
    though using asynctask for long tasks is not advisable
    because it blocks other asynctasks from running
     */


//    private class HangboardTask extends AsyncTask<String, Integer, String[]> {
//
//        @Override
//        protected void onPreExecute() {
//            textView_hangboardResult.setText("It begins...");
//        }
//
//        @Override
//        protected String[] doInBackground(String... params) {
//
//            for (int i = 0; i < Integer.parseInt(params[2]); i++ ) {
//
//                for (int j = 0; j <= Integer.parseInt(params[1]); j++ ) {
//                    publishProgress(j, 1);
//                    try {
//                        Thread.sleep(999);
//                    } catch (InterruptedException e) {
//                        Log.d("Ekseptioni", "Thread interrupted");
//                    }
//                }
//
//                for (int j = 0; j <= Integer.parseInt(params[0]); j++ ) {
//                    publishProgress(j, 0);
//                    try {
//                        Thread.sleep(999);
//                    } catch (InterruptedException e) {
//                        Log.d("Ekseptioni", "Thread interrupted");
//                    }
//                }
//
//            }
//            return params;
//        }
//
//        @Override
//        protected void onProgressUpdate(Integer... values) {
//            switch (values[1]) {
//                case 0:
//                    textView_timer.setTextColor(Color.GREEN);
//                    break;
//                case 1:
//                    textView_timer.setTextColor(Color.RED);
//                    break;
//            }
//            textView_timer.setText(Integer.toString(values[0]));
//        }
//
//        @Override
//        protected void onPostExecute(String[] params) {
//            textView_timer.setText("");
//            addHangboardPresenter.addHang(params);
//        }
//    }

}
