package me.climbingti.climbingtrainer.common.view;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;

import com.gordonwong.materialsheetfab.AnimatedFab;

import me.climbingti.climbingtrainer.R;

/**
 * Created by Aleksi on 24.10.2015.
 */
public class Fab extends FloatingActionButton implements AnimatedFab {

    private static final int FAB_ANIM_DURATION = 25;


    public Fab(Context context) {
        super(context);
    }

    public Fab(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Fab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void show(){
        show(0, 0);
    }

    @Override
    public void show(float translationX, float translationY) {
        setTranslation(translationX, translationY);

        if(getVisibility() != View.VISIBLE){
            float pivotX = getPivotX() + translationX;
            float pivotY = getPivotY() + translationY;

            ScaleAnimation anim;

            if(pivotX == 0 || pivotY == 0){
                anim = new ScaleAnimation(0,1,0,1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            } else {
                anim = new ScaleAnimation(0,1,0,1, pivotX, pivotY);
            }

            anim.setDuration(FAB_ANIM_DURATION);
            anim.setInterpolator(getInterpolator());
            startAnimation(anim);
        }
        setVisibility(View.VISIBLE);
    }

    public void hide(){
        if (getVisibility() == View.VISIBLE){
            float pivotX = getPivotX() + getTranslationX();
            float pivotY = getPivotY() + getTranslationY();

            ScaleAnimation anim = new ScaleAnimation(1,0,1,0, pivotX, pivotY);
            anim.setDuration(FAB_ANIM_DURATION);
            anim.setInterpolator(getInterpolator());
            startAnimation(anim);
        }
        setVisibility(View.GONE);
    }

    private void setTranslation(float translationX, float translationY) {
        animate().setInterpolator(getInterpolator()).setDuration(FAB_ANIM_DURATION)
                .translationX(translationX).translationY(translationY);
    }

    private Interpolator getInterpolator() {
        return AnimationUtils.loadInterpolator(getContext(), R.interpolator.msf_interpolator);
    }
}
