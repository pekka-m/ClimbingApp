package me.climbingti.climbingtrainer.common;

import android.content.Context;

import me.climbingti.climbingtrainer.campus.CampusInteractor;
import me.climbingti.climbingtrainer.campus.FakeCampusMapperImpl;
import me.climbingti.climbingtrainer.bouldering.ClimbInteractor;
import me.climbingti.climbingtrainer.bouldering.FakeClimbMapperImpl;
import me.climbingti.climbingtrainer.hangboard.Domain.HangboardInteractor;
import me.climbingti.climbingtrainer.hangboard.FakeHangboardMapperImpl;
import me.climbingti.climbingtrainer.practice.FakePracticeMapperImpl;
import me.climbingti.climbingtrainer.practice.PracticeInteractor;
import me.climbingti.climbingtrainer.settings.SettingsInteractor;
import me.climbingti.climbingtrainer.statistics.StatisticsInteractor;
import me.climbingti.climbingtrainer.util.DateConverter;

/**
 * Created by Aleksi on 30.12.2015.
 * in me.climbingti.climbingtrainer.common
 *
 * Enables injection of mock implementations for factories at compile time.
 * This is useful for testing since it allows us to use a fake instance
 * of the class to isolate the dependencies and run a test hermetically.
 */

// TODO: 30.12.2015 for some reason these methods need context even though they do not use it?
public class Injection {
    public static CampusInteractor createCampusInteractor(Context context){
        Mapper mapper = new FakeCampusMapperImpl();
        return new CampusInteractor(mapper, createPracticeInteractor(context));
    }

    public static ClimbInteractor createClimbInteractor(Context context){
        Mapper mapper = new FakeClimbMapperImpl();
        return new ClimbInteractor(mapper, createPracticeInteractor(context));
    }

    public static HangboardInteractor createHangboardInteractor(Context context){
        Mapper mapper = new FakeHangboardMapperImpl();
        return new HangboardInteractor(mapper, createPracticeInteractor(context));
    }

    public static StatisticsInteractor createStatisticsInteractor(Context context){
        Mapper practiceMapper = new FakePracticeMapperImpl();
        DateConverter dateConverter = new DateConverter();
        Mapper climbSQLiteMapper = new FakeClimbMapperImpl();
        Mapper campusSQLiteMapper = new FakeCampusMapperImpl();
        Mapper hangboardSQLiteMapper = new FakeHangboardMapperImpl();
        return new StatisticsInteractor(practiceMapper, dateConverter, climbSQLiteMapper, campusSQLiteMapper, hangboardSQLiteMapper);
    }

    public static PracticeInteractor createPracticeInteractor(Context context){
        FakePracticeMapperImpl mapper = new FakePracticeMapperImpl();
        return new PracticeInteractor(mapper);
    }

    public static SettingsInteractor createSettingsInteractor(Context context){
        SharedPreferencesManagerInterface manager = new SharedPreferencesManager(context);
        return new SettingsInteractor(manager);
    }

    // TODO: 30.12.2015 make a mock of this method
    public static SharedPreferencesManagerInterface createSharedPrefManager(Context context){
        return new FakeSharedPreferencesManagerImpl();
    }
}