package me.climbingti.climbingtrainer.feed;

import android.content.Context;

import org.junit.Test;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import me.climbingti.climbingtrainer.campus.addcampus.AddCampusContract;
import me.climbingti.climbingtrainer.campus.CampusInteractor;
import me.climbingti.climbingtrainer.campus.addcampus.CampusPresenter;
import me.climbingti.climbingtrainer.common.Injection;
import me.climbingti.climbingtrainer.common.SharedPreferencesManagerInterface;
import me.climbingti.climbingtrainer.hangboard.Domain.HangboardInteractor;
import me.climbingti.climbingtrainer.hangboard.FakeHangboardLayoutMapper;
import me.climbingti.climbingtrainer.statistics.StatisticsInteractor;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

/**
 * Created by Aleksi on 2.1.2016.
 * in me.climbingti.climbingtrainer.feed
 */
public class FeedPresenterSpec {

    @Mock
    Context context;

//    @Mock
//    private SharedPreferencesManagerInterface preferencesManager;

    @Mock
    private StatisticsInteractor statisticsInteractor;

    @Mock
    private FeedContract.View view;

    private FeedPresenter presenter;

    @Before
    public void setUpFeedPresenter(){

        MockitoAnnotations.initMocks(this);

        presenter = new FeedPresenter(statisticsInteractor, Injection.createSharedPrefManager(context), view);

    }


    @Test
    public void itShouldLoadCards() throws Exception{
        presenter.loadCards();
        verify(statisticsInteractor).getDataForPracticeCards(3);
    }

    @Test
    public void itShouldShowCards() throws Exception {
        presenter.loadCards();
        verify(view).showCards(null);
    }
}