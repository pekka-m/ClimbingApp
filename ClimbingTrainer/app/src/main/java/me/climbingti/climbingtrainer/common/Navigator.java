package me.climbingti.climbingtrainer.common;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import me.climbingti.climbingtrainer.hangboard.addhangboard.HangboardActivity;
import me.climbingti.climbingtrainer.campus.addcampus.CampusActivity;
import me.climbingti.climbingtrainer.editor.hangboard.HangboardEditorActivity;
import me.climbingti.climbingtrainer.overview.OverviewActivity;
import me.climbingti.climbingtrainer.practice.browser.PracticeBrowserActivity;
import me.climbingti.climbingtrainer.practice.detail.PracticeDetailActivity;

import me.climbingti.climbingtrainer.settings.SettingsActivity;


/**
 * Created by Aleksi on 24.10.2015.
 */
public class Navigator {

    public void navigateToCampusActivity(Context context){
        if (context != null){
            Intent intentToLaunch = CampusActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

    public void navigateToHangboardActivity(Context context){
        if (context != null){
            Intent intentToLaunch = HangboardActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

    /**
     * navigates to the practice detail activity
     * @param context context of the application
     * @param practiceId the id of the practice we want to open
     */
    public void navigateToPracticeDetailActivity(Context context, int practiceId) {
        if (context != null) {
            Log.d("navigator", "navigateToPracticeDetailActivity: pracice id " + practiceId);
            Intent intentToLaunch = PracticeDetailActivity.getCallingIntent(context, practiceId);
            context.startActivity(intentToLaunch);
        }
    }


    public void navigateToPracticeBrowserActivity(Context context) {
        if (context != null) {
            Intent intentToLaunch = PracticeBrowserActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

    public void navigateToSettings (Context context) {
        if (context != null) {
            Intent intentToLaunch = SettingsActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }
    public void navigateToHangboardEditorActivity(Context context) {
        if (context != null){
            Intent intentToLaunch = HangboardEditorActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

    public void navigateToOverviewActivity(Context context) {
        if (context != null){
            Intent intentToLaunch = OverviewActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

}
