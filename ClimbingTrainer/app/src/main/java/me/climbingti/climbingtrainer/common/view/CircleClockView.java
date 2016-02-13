package me.climbingti.climbingtrainer.common.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.SystemClock;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import me.climbingti.climbingtrainer.R;
import me.climbingti.climbingtrainer.util.Utils;

/**
 * Created by Aleksi on 28.12.2015.
 * in me.climbingti.climbingtrainer.common.view
 */
public class CircleClockView extends View{


    public interface OnCycleFinishListener{
        void onCycleFinish();
    }
    public void setOnCycleFinishListener(OnCycleFinishListener onCycleFinishListener){
        this.oncycleFinishListener = onCycleFinishListener;
    }

    private OnCycleFinishListener oncycleFinishListener;
    private int mAccentColor;
    private int mWhiteColor;
    private long mIntervalTime = 0;
    private long mIntervalStartTime = -1;
    private long mCurrentIntervalTime = 0;
    private long mAccumulatedTime = 0;
    private boolean mPaused = false;
    private boolean mAnimate = false;
    private static float mStrokeSize = 4;
    private static float mMarkerStrokeSize = 2;
    private final Paint mPaint = new Paint();
    private final Paint mTestPaint = new Paint();

    private final Paint mFill = new Paint();
    private final RectF mArcRect = new RectF();
    private float mRadiusOffset;   // amount to remove from radius to account for markers on circle

    // Stopwatch mode is the default.
    private boolean mTimerMode = false;

    @SuppressWarnings("unused")
    public CircleClockView(Context context) {
        this(context, null);
    }

    // called when view is created
    public CircleClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //used to init stuff
        init(context);
    }

    public void setIntervalTime(long t) {
        mIntervalTime = t;
        postInvalidate();
    }

    public void setmAccentColor(int mAccentColor) {
        this.mAccentColor = mAccentColor;
    }

    public void reset() {
        mIntervalStartTime = -1;
        postInvalidate();
    }

    public void startIntervalAnimation() {
        mIntervalStartTime = SystemClock.elapsedRealtime();
        mAnimate = true;
        Log.d("circle", "startIntervalAnimation called");
        invalidate();

        mPaused = false;
    }

    public void stopIntervalAnimation() {
        mAnimate = false;
        mIntervalStartTime = -1;
        mAccumulatedTime = 0;
    }

    public boolean isAnimating() {
        return (mIntervalStartTime != -1);
    }

    public void pauseIntervalAnimation() {
        mAnimate = false;
        mAccumulatedTime += SystemClock.elapsedRealtime() - mIntervalStartTime;
        mPaused = true;
    }

    public void abortIntervalAnimation() {
        mAnimate = false;
    }

    public void setPassedTime(long time, boolean drawRed) {
        // The onDraw() method checks if mIntervalStartTime has been set before drawing any red.
        // Without drawRed, mIntervalStartTime should not be set here at all, and would remain at -1
        // when the state is reconfigured after exiting and re-entering the application.
        // If the timer is currently running, this drawRed will not be set, and will have no effect
        // because mIntervalStartTime will be set when the thread next runs.
        // When the timer is not running, mIntervalStartTime will not be set upon loading the state,
        // and no red will be drawn, so drawRed is used to force onDraw() to draw the red portion,
        // despite the timer not running.
        mCurrentIntervalTime = mAccumulatedTime = time;
        if (drawRed) {
            mIntervalStartTime = SystemClock.elapsedRealtime();
        }
        postInvalidate();
    }


    // used to init stuff
    private void init(Context c) {

        //getting context from resources
        Resources resources = c.getResources();
        mStrokeSize = resources.getDimension(R.dimen.circletimer_circle_size); // 4dp
        float dotDiameter = resources.getDimension(R.dimen.circletimer_dot_size); //12dp
        mMarkerStrokeSize = resources.getDimension(R.dimen.circletimer_marker_size); // 16dp
        // calculating radius offset, by calculating largest from given values
        mRadiusOffset = Utils.calculateRadiusOffset(
                mStrokeSize, dotDiameter, mMarkerStrokeSize);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mTestPaint.setAntiAlias(true);
        mTestPaint.setStyle(Paint.Style.STROKE);
        mWhiteColor = ContextCompat.getColor(c ,R.color.timerBg);
        mAccentColor = Color.GREEN;
        mFill.setAntiAlias(true);
        mFill.setStyle(Paint.Style.FILL);
        mFill.setColor(mAccentColor);
    }


    //sets the timer mode
    public void setTimerMode(boolean mode) {
        mTimerMode = mode;
    }

    @Override
    public void onDraw(Canvas canvas) {
        // calculates the center of the view
        int xCenter = getWidth() / 2 + 1;
        int yCenter = getHeight() / 2;

        // sets the width of background circle
        mPaint.setStrokeWidth(mStrokeSize);

        // sets the width of the elapsed time arc
        mTestPaint.setStrokeWidth(10);

        // calculating the radius of the circle
        float radius = Math.min(xCenter, yCenter) - mRadiusOffset;

        // if interval startTime == -1 then timer is not running
        if (mIntervalStartTime == -1) {
            // just draw a complete white circle, no red arc needed
            mPaint.setColor(mWhiteColor);
            canvas.drawCircle(xCenter, yCenter, radius, mPaint);
        } else {
            // if app is in animate mode
            if (mAnimate) {

                // currentIntervalTime is
                mCurrentIntervalTime =
                        // elapsedRealTIme returns the time since system was booted, and includes deep sleep
                        // runs even when cpu is in powersaving modes, and is monotonic
                        SystemClock.elapsedRealtime() - mIntervalStartTime + mAccumulatedTime;
            }

            //draw a combination of red and white arcs to create a circle
            // setting the bounds?
            mArcRect.top = yCenter - radius;
            mArcRect.bottom = yCenter + radius;
            mArcRect.left = xCenter - radius;
            mArcRect.right = xCenter + radius;

            // percentage of elapsed time
            float redPercent = (float) mCurrentIntervalTime / (float) mIntervalTime;

            // prevent timer from doing more than one full circle
            redPercent = (redPercent > 1 && mTimerMode) ? 1 : redPercent;
            Log.i("Circle", "onDraw: redPercent: " + Float.toString(redPercent));


            // if the percentage is over 1, then the clock is reset
            if (redPercent >= 1){
                oncycleFinishListener.onCycleFinish();
            }

            // used to calculate the remaining percent of the background circle
            float whitePercent = 1 - (redPercent > 1 ? 1 : redPercent);


            // draws the red elapsed time arc
            // draw red arc here

            // should these be somewhere else than here?
            mTestPaint.setColor(mAccentColor);
            mTestPaint.setStrokeWidth(25);

            if (mTimerMode) {
                canvas.drawArc(mArcRect, 270, -redPercent * 360, false, mTestPaint);
            } else {
                canvas.drawArc(mArcRect, 270, +redPercent * 360, false, mTestPaint);
            }

            // draws the remaining background arc here
            // draw white arc here

            // should these be somewhere else than here?
            mPaint.setStrokeWidth(mStrokeSize);
            mPaint.setColor(mWhiteColor);

            if (mTimerMode) {
                canvas.drawArc(mArcRect, 270, +whitePercent * 360, false, mPaint);
            } else {
                canvas.drawArc(mArcRect, 270 + (1 - whitePercent) * 360,
                        whitePercent * 360, false, mPaint);
            }
        }
        if (mAnimate) {

            postInvalidateOnAnimation();
        }
    }
}