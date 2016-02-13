package me.climbingti.climbingtrainer.hangboard.addhangboard;

import android.content.Context;

import me.climbingti.climbingtrainer.common.Injection;
import me.climbingti.climbingtrainer.hangboard.FakeHangboardLayoutMapper;
import me.climbingti.climbingtrainer.hangboard.Domain.HangboardInteractor;
import me.climbingti.climbingtrainer.common.Collection;
import me.climbingti.climbingtrainer.common.Mapper;
import me.climbingti.climbingtrainer.common.SQLiteHelper;
import me.climbingti.climbingtrainer.statistics.StatisticsInteractor;

/**
 * Created by Pekka Melgin on 22.10.2015.
 */
public class AddHangboardPresenter implements AddHangboardContract.Presenter {

    private HangboardInteractor hangboardInteractor;
    private StatisticsInteractor statisticsInteractor;
    private Mapper layoutMapper;
    private AddHangboardContract.View view;


    public AddHangboardPresenter(HangboardInteractor hangboardInteractor,
                                 StatisticsInteractor statisticsInteractor,
                                 Mapper layoutMapper, AddHangboardContract.View view) {
        this.hangboardInteractor = hangboardInteractor;
        this.statisticsInteractor = statisticsInteractor;
        this.layoutMapper = layoutMapper;
        this.view = view;
    }

    /**
     * Adds hangboard exercise to db by calculating hangTime
     * * reps.
     * @param times [0] = hangTime, [1] = restTime, [2] = reps
     */
    public void addHang(String[] times) {
        try {
            int time = Integer.parseInt(times[0]) * Integer.parseInt(times[2]);
            this.hangboardInteractor.addHang(time);
            this.view.showHangboardRepAdded();
        } catch (Exception e) {
            this.view.showDatabaseError();
        }
    }

    @Override
    public Collection loadHangboardingSessions() {
        try {
            return layoutMapper.fetchAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addHangboardRep(String hangTime, String restTime, String reps) {
        try {
            int time = Integer.parseInt(hangTime) * Integer.parseInt(reps);
            this.hangboardInteractor.addHang(time);
            this.view.showHangboardRepAdded();
        } catch (Exception e) {
            this.view.showDatabaseError();
        }
    }
}