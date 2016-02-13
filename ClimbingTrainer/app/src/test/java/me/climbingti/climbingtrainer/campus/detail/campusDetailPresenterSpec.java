package me.climbingti.climbingtrainer.campus.detail;


import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import me.climbingti.climbingtrainer.bouldering.ClimbInteractor;
import me.climbingti.climbingtrainer.campus.CampusCollection;
import me.climbingti.climbingtrainer.campus.FakeCampusMapperImpl;
import me.climbingti.climbingtrainer.campus.addcampus.AddCampusContract;
import me.climbingti.climbingtrainer.campus.CampusInteractor;
import me.climbingti.climbingtrainer.campus.addcampus.CampusPresenter;
import me.climbingti.climbingtrainer.common.Injection;
import me.climbingti.climbingtrainer.common.Mapper;
import me.climbingti.climbingtrainer.practice.FakePracticeMapperImpl;
import me.climbingti.climbingtrainer.practice.PracticeInteractor;
import me.climbingti.climbingtrainer.statistics.StatisticsInteractor;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
/**
 * Created by Aleksi on 30.12.2015.
 * in me.climbingti.climbingtrainer.campus.detail
 */
public class campusDetailPresenterSpec {


    @Mock
    private CampusInteractor campusInteractor;

    @Mock
    private CampusdetailContract.View view;

    private CampusDetailPresenter presenter;

    private ArgumentCaptor<CampusCollection> argumentCaptor;

    @Before

    public void setUpCampusDetailPresenter() {

        MockitoAnnotations.initMocks(this);

//        campusInteractor = new CampusInteractor(new FakeCampusMapperImpl(), new PracticeInteractor(new FakePracticeMapperImpl()));

        presenter = new CampusDetailPresenter(campusInteractor, view);

        argumentCaptor = ArgumentCaptor.forClass(CampusCollection.class);;
    }

    /**
     * verifies that presenter calls showCampusReps
     */
    @Test
    public void itShouldShowCampusReps() {
        presenter.loadCampusReps(2);
        verify(view).showCampusReps(argumentCaptor.capture());
    }

    /**
     * verifies that presenter calls interactor get campus reps
     */
    @Test
    public void itShouldLoadCampusReps() {
        presenter.loadCampusReps(2);
        verify(campusInteractor).fetchByPracticeId(2);
    }
}