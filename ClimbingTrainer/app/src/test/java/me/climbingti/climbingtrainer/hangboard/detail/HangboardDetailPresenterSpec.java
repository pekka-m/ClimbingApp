package me.climbingti.climbingtrainer.hangboard.detail;

import org.junit.Test;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import me.climbingti.climbingtrainer.campus.addcampus.AddCampusContract;
import me.climbingti.climbingtrainer.campus.CampusInteractor;
import me.climbingti.climbingtrainer.campus.addcampus.CampusPresenter;
import me.climbingti.climbingtrainer.hangboard.Domain.HangboardInteractor;
import me.climbingti.climbingtrainer.hangboard.FakeHangboardLayoutMapper;
import me.climbingti.climbingtrainer.statistics.StatisticsInteractor;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

/**
 * Created by Aleksi on 2.1.2016.
 * in me.climbingti.climbingtrainer.hangboard.detail
 */
public class HangboardDetailPresenterSpec {

    @Mock
    private HangboardInteractor hangboardInteractor;

    @Mock
    private HangboardDetailContract.View view;

    private HangboardDetailPresenter presenter;

    @Before
    public void setUpHangboardDetailPresenter() {

        MockitoAnnotations.initMocks(this);

        presenter = new HangboardDetailPresenter(hangboardInteractor, view);
    }

    @Test
    public void itShouldLoadHangboardReps() {
        presenter.loadHangboardReps(2);
        verify(hangboardInteractor).fetchByPracticeId(2);
    }

    @Test
    public void itShouldShowHangboardReps() {
        presenter.loadHangboardReps(2);
        verify(view).showHangboardReps(null);
    }
}