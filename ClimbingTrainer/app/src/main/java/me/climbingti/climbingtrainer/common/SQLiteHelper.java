package me.climbingti.climbingtrainer.common;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Pekka Melgin on 23.10.2015.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ClimbDB";

    public static final String TABLE_CLIMB = "Climb";
    public static final String TABLE_CAMPUS = "Campus";
    public static final String TABLE_HANGBOARD = "Hangboard";
    public static final String TABLE_PRACTICE = "Practice";

    public static final String KEY_ID = "_id";
    public static final String KEY_PRACTICE_ID = "Practice";
    public static final String KEY_GRADE = "Grade";
    public static final String KEY_STEPS = "Steps";
    public static final String KEY_TIME = "Time";
    public static final String KEY_DATETIME = "DateTime";
    public static final String KEY_STARTTIME = "StartTime";
    public static final String KEY_ENDTIME = "EndTime";
    public static final String KEY_CAMPUSTYPE = "CampusType";

    public static final int ID_INDEX = 0;
    public static final int PRACTICE_ID_INDEX = 1;
    public static final int STARTTIME_INDEX= 1;
    public static final int GRADE_INDEX = 2;
    public static final int STEPS_INDEX = 2;
    public static final int TIME_INDEX = 2;
    public static final int ENDTIME_INDEX = 2;
    public static final int DATETIME_INDEX = 3;
    public static final int CAMPUSTYPE_INDEX = 4;

    private static final String CREATE_TABLE_CLIMB = "CREATE TABLE " + TABLE_CLIMB +
            " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_PRACTICE_ID + " INTEGER, " +
            KEY_GRADE + " INTEGER, " +
            KEY_DATETIME + " DATETIME DEFAULT (DATETIME(CURRENT_TIMESTAMP, 'LOCALTIME')));";

    private static final String CREATE_TABLE_CAMPUS = "CREATE TABLE " + TABLE_CAMPUS
            + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_PRACTICE_ID + " INTEGER, " +
            KEY_STEPS + " INTEGER, " +
            KEY_DATETIME + " DATETIME DEFAULT (DATETIME(CURRENT_TIMESTAMP, 'LOCALTIME')), " +
            KEY_CAMPUSTYPE +" TEXT" +
            ");";

    private static final String CREATE_TABLE_HANGBOARD = "CREATE TABLE " + TABLE_HANGBOARD +
            " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_PRACTICE_ID + " INTEGER, " +
            KEY_TIME + " INTEGER, " +
            KEY_DATETIME + " DATETIME DEFAULT (DATETIME(CURRENT_TIMESTAMP, 'LOCALTIME')));";

    private static final String CREATE_TABLE_PRACTICE = "CREATE TABLE " + TABLE_PRACTICE +
            " (" + KEY_PRACTICE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_STARTTIME + " DATETIME DEFAULT (DATETIME(CURRENT_TIMESTAMP, 'LOCALTIME')), " +
            KEY_ENDTIME + " DATETIME);";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CLIMB);
        db.execSQL(CREATE_TABLE_CAMPUS);
        db.execSQL(CREATE_TABLE_HANGBOARD);
        db.execSQL(CREATE_TABLE_PRACTICE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIMB);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAMPUS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HANGBOARD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRACTICE);
    }
}
