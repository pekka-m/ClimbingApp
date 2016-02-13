package me.climbingti.climbingtrainer.campus.addcampus;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import me.climbingti.climbingtrainer.campus.CampusInteractor;
import me.climbingti.climbingtrainer.statistics.StatisticsInteractor;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;


/**
 * Created by Aleksi on 30.12.2015.
 * in me.climbingti.climbingtrainer
 */
public class AddCampusPresenterSpec {

    @Mock
    private CampusInteractor campusInteractor;

    @Mock
    private StatisticsInteractor statisticsInteractor;

    @Mock
    private AddCampusContract.View campusView;

    private CampusPresenter campusPresenter;

    @Before
    public  void setUpCampusPresenter(){
        MockitoAnnotations.initMocks(this);

        campusPresenter = new CampusPresenter(campusInteractor, campusView);
    }

    @Test
    public void itShouldShowSnackBarWhenPressingButton(){
        campusPresenter.addNewCampusRep("1-2-3", "double");

        verify(campusView).showRepSaved();

    }

}