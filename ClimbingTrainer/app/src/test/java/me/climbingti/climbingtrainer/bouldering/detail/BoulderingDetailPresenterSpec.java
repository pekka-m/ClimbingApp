package me.climbingti.climbingtrainer.bouldering.detail;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import me.climbingti.climbingtrainer.bouldering.ClimbInteractor;
import me.climbingti.climbingtrainer.campus.addcampus.AddCampusContract;
import me.climbingti.climbingtrainer.campus.CampusInteractor;
import me.climbingti.climbingtrainer.campus.addcampus.CampusPresenter;
import me.climbingti.climbingtrainer.common.Injection;
import me.climbingti.climbingtrainer.statistics.StatisticsInteractor;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;


/**
 * Created by Aleksi on 30.12.2015.
 * in me.climbingti.climbingtrainer.bouldering.detail
 */
public class BoulderingDetailPresenterSpec {

    @Mock
    private ClimbInteractor climbInteractor;

    @Mock
    private BoulderingDetailContract.View view;

    private BoulderingDetailPresenter presenter;

    @Before
    public void setUpBoulderingDetailsPresenter(){

        MockitoAnnotations.initMocks(this);

        presenter = new BoulderingDetailPresenter(climbInteractor, view);
    }



    @Test
    public void itShouldShowBoulderingSends(){
        presenter.loadBoulderingSends(1);

        verify(view).showBoulderingSends(null);
    }

    @Test
    public void itShouldLoadBoulderingSends(){
        presenter.loadBoulderingSends(1);

        verify(climbInteractor).fetchByPracticeId(1);
    }
}