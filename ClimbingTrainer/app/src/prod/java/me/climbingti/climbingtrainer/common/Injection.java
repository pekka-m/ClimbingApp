package me.climbingti.climbingtrainer.common;

import android.content.Context;

import me.climbingti.climbingtrainer.campus.CampusInteractor;
import me.climbingti.climbingtrainer.campus.CampusSQLiteMapper;
import me.climbingti.climbingtrainer.bouldering.ClimbInteractor;
import me.climbingti.climbingtrainer.bouldering.ClimbSQLiteMapper;
import me.climbingti.climbingtrainer.hangboard.DataAccess.HangboardSQLiteMapper;
import me.climbingti.climbingtrainer.hangboard.Domain.HangboardInteractor;
import me.climbingti.climbingtrainer.practice.PracticeInteractor;
import me.climbingti.climbingtrainer.practice.PracticeSQLiteMapper;
import me.climbingti.climbingtrainer.statistics.StatisticsInteractor;

/**
 * Created by Aleksi on 30.12.2015.
 * in me.climbingti.climbingtrainer.common
 */

/**
 * Enables injection of production implementations for interactors at compile time
 *
 */
public class Injection {
    public static CampusInteractor createCampusInteractor(Context context){
        Mapper mapper = new CampusSQLiteMapper(context);
        return new CampusInteractor(mapper, createPracticeInteractor(context));
    }

    public static ClimbInteractor createClimbInteractor(Context context){
        Mapper mapper = new ClimbSQLiteMapper(context);
        return new ClimbInteractor(mapper, createPracticeInteractor(context));
    }

    public static HangboardInteractor createHangboardInteractor(Context context){
        Mapper mapper = new HangboardSQLiteMapper(context);
        return new HangboardInteractor(mapper, createPracticeInteractor(context));
    }

    public static StatisticsInteractor createStatisticsInteractor(Context context){
        Mapper practiceMapper = new PracticeSQLiteMapper(context);
        DateConverter dateConverter = new DateConverter();
        Mapper climbSQLiteMapper = new ClimbSQLiteMapper(context);
        Mapper campusSQLiteMapper = new CampusSQLiteMapper(context);
        Mapper hangboardSQLiteMapper = new HangboardSQLiteMapper(context);
        return new StatisticsInteractor(practiceMapper, dateConverter, climbSQLiteMapper, campusSQLiteMapper, hangboardSQLiteMapper);
    }

    public static PracticeInteractor createPracticeInteractor(Context context){
        PracticeSQLiteMapper mapper = new PracticeSQLiteMapper(context);
        return new PracticeInteractor(mapper);
    }

    public static SettingsInteractor createSettingsInteractor(Context context){
        SharedPreferencesManager manager = new SharedPreferencesManager(context);
        return new SettingsInteractor(manager);
    }

    public static SharedPreferencesManagerInterface createSharedPrefManager(Context context){
        return new SharedPreferencesManager(context);
    }
}
