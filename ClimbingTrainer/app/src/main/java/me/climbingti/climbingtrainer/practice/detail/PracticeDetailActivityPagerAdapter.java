package me.climbingti.climbingtrainer.practice.detail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import me.climbingti.climbingtrainer.bouldering.detail.BoulderingDetailFragment;
import me.climbingti.climbingtrainer.hangboard.detail.HangboardDetailFragment;
import me.climbingti.climbingtrainer.campus.detail.CampusDetailFragment;

/**
 * Created by H3173 on 19.11.2015.
 */
public class PracticeDetailActivityPagerAdapter extends FragmentPagerAdapter {

    private int id;

    public PracticeDetailActivityPagerAdapter(FragmentManager fm, int id) {
        super(fm);
        this.id = id;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return PracticeDetailFragment.newInstance(position, this.id);
            case 1:
                return BoulderingDetailFragment.newInstance(position, this.id);
            case 2:
                return HangboardDetailFragment.newInstance(position, this.id);
            case 3:
                return CampusDetailFragment.newInstance(position, this.id);
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
                return "Overview";
            case 1:
                return "Bouldering";
            case 2:
                return "Hangboard";
            case 3:
                return "Campus";
        }
        return null;
    }
}
