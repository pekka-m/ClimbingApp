package me.climbingti.climbingtrainer.mainview;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import me.climbingti.climbingtrainer.feed.FeedFragment;
import me.climbingti.climbingtrainer.mainview.bouldering.BoulderingFragment;
import me.climbingti.climbingtrainer.mainview.campus.CampusFragment;
import me.climbingti.climbingtrainer.mainview.hanbgoarding.HangboardFragment;

/**
 * Created by Aleksi on 14.10.2015.
 */
public class MainActivityPagerAdapter extends FragmentPagerAdapter {

    public MainActivityPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return FeedFragment.newInstance(position);
            case 1:
                return BoulderingFragment.newInstance(position);
            case 2:
                return HangboardFragment.newInstance(position);
            case 3:
                return CampusFragment.newInstance(position);
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "feed";
            case 1:
                return "indoor climb";
            case 2:
                return "hangboard";
            case 3:
                return "campus";
        }
        return null;
    }
}