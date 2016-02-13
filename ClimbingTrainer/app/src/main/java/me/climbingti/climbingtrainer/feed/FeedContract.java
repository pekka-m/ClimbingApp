package me.climbingti.climbingtrainer.feed;

import me.climbingti.climbingtrainer.common.Collection;

/**
 * Created by Aleksi on 30.12.2015.
 * in me.climbingti.climbingtrainer.feed
 */
public interface FeedContract {

    interface View{

        // TODO: 30.12.2015 rename into something more sensible
        void showCards(Collection entities);
    }

    interface Presenter{
        void loadCards() throws Exception;
    }
}
