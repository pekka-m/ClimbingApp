package me.climbingti.climbingtrainer.hangboard.domain;


import android.content.Context;

import org.junit.Test;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import me.climbingti.climbingtrainer.campus.addcampus.AddCampusContract;
import me.climbingti.climbingtrainer.campus.CampusInteractor;
import me.climbingti.climbingtrainer.campus.addcampus.CampusPresenter;
import me.climbingti.climbingtrainer.common.Entity;
import me.climbingti.climbingtrainer.common.Injection;
import me.climbingti.climbingtrainer.common.Mapper;
import me.climbingti.climbingtrainer.hangboard.Domain.HangboardEntity;
import me.climbingti.climbingtrainer.hangboard.Domain.HangboardInteractor;
import me.climbingti.climbingtrainer.hangboard.FakeHangboardLayoutMapper;
import me.climbingti.climbingtrainer.hangboard.FakeHangboardMapperImpl;
import me.climbingti.climbingtrainer.practice.PracticeInteractor;
import me.climbingti.climbingtrainer.statistics.StatisticsInteractor;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
/**
 * Created by Aleksi on 2.1.2016.
 * in me.climbingti.climbingtrainer.hangboard.domain
 */
public class HangboardInteractorSpec {

    @Mock
    private Context context;

    @Mock
    private Mapper hangboardMapper;

    @Mock
    private PracticeInteractor practiceInteractor;

    @Mock
    private HangboardInteractor hangboardInteractor;

    @Before
    public void setUpHangboardInteractor(){
        hangboardInteractor = new HangboardInteractor(hangboardMapper,practiceInteractor);
    }
    @Test
    public void itShouldAddHangboardRep() throws Exception{
        // TODO: 2.1.2016 NPE in test
        hangboardInteractor.addHang(12);
        verify(hangboardMapper).store(null);
    }

}