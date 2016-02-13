package me.climbingti.climbingtrainer.bouldering;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import me.climbingti.climbingtrainer.bouldering.ClimbInteractor;
import me.climbingti.climbingtrainer.campus.addcampus.AddCampusContract;
import me.climbingti.climbingtrainer.campus.CampusInteractor;
import me.climbingti.climbingtrainer.campus.addcampus.CampusPresenter;
import me.climbingti.climbingtrainer.common.Injection;
import me.climbingti.climbingtrainer.common.Mapper;
import me.climbingti.climbingtrainer.practice.FakePracticeMapperImpl;
import me.climbingti.climbingtrainer.practice.PracticeInteractor;
import me.climbingti.climbingtrainer.statistics.StatisticsInteractor;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.verify;



/**
 * Created by Aleksi on 2.1.2016.
 * in me.climbingti.climbingtrainer.bouldering
 */
public class ClimbInteractorSpec {

    private PracticeInteractor practiceInteractor;

    private Mapper boulderingMapper;

    @Mock
    private PracticeInteractor mockedPracticeInteractor;
    @Mock
    private Mapper mockedBoulderingMapper;


    private ClimbInteractor mockedClimbInteractor;


    private ClimbInteractor climbInteractor;

    private         ArgumentCaptor<ClimbEntity> argument;


    @Before
    public void setUpClimbInteractor(){
        MockitoAnnotations.initMocks(this);
        boulderingMapper = new FakeClimbMapperImpl();
        practiceInteractor = new PracticeInteractor(new FakePracticeMapperImpl());
        climbInteractor = new ClimbInteractor(boulderingMapper, practiceInteractor);

        mockedClimbInteractor = new ClimbInteractor(mockedBoulderingMapper, mockedPracticeInteractor);

        argument = ArgumentCaptor.forClass(ClimbEntity.class);
    }


    @Test
    public void itShouldAddBoulderingSendToDb() throws Exception{

//        climbInteractor.addClimb(1);
       // verify(boulderingMapper).store(null);

        mockedClimbInteractor.addClimb(1);
        verify(mockedBoulderingMapper).store(argument.capture());
        assertThat(argument.getValue(), instanceOf(ClimbEntity.class));
    }

    @Test
    public void itShouldFetchAllBoulderingSends() throws Exception{
        mockedClimbInteractor.fetchAll();
        verify(mockedBoulderingMapper).fetchAll();
    }

    @Test
    public void itShouldFetchAllBoulderingSendsByPracticeId() throws Exception{
        mockedClimbInteractor.fetchByPracticeId(2);
        verify(mockedBoulderingMapper).fetchByPracticeId(2);
    }

}