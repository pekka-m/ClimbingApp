package me.climbingti.climbingtrainer.hangboard.addhangboard;

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
 * Created by Aleksi on 30.12.2015.
 * in me.climbingti.climbingtrainer.hangboard.addhangboard
 */
public class AddHangboardPresenterSpec {

    @Mock
    private HangboardInteractor hangboardInteractor;

    @Mock
    private StatisticsInteractor statisticsInteractor;

    @Mock
    private FakeHangboardLayoutMapper fakeHangboardLayoutMapper;

    @Mock
    private AddHangboardContract.View view;


    private AddHangboardPresenter presenter;


    @Before
    public void setUpAddHangboardPresenter(){
        MockitoAnnotations.initMocks(this);

        presenter = new AddHangboardPresenter(hangboardInteractor, statisticsInteractor, fakeHangboardLayoutMapper, view);
    }

    @Test
    public void itShouldShowSnackBarWhenRepAdded(){
        presenter.addHangboardRep("2", "2", "2");

        verify(view).showHangboardRepAdded();
    }

    @Test
    public void itShouldLoadHangboardingSessions() throws Exception{
        presenter.loadHangboardingSessions();

        verify(fakeHangboardLayoutMapper).fetchAll();
    }

    @Test
    public void itShouldSaveHangboardingRep() throws Exception{
        presenter.addHangboardRep("2", "2", "2");

        verify(hangboardInteractor).addHang(4);

    }
}