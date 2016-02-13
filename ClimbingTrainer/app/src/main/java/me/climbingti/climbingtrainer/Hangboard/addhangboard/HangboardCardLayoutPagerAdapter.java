package me.climbingti.climbingtrainer.hangboard.addhangboard;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import me.climbingti.climbingtrainer.hangboard.Domain.HangboardLayout;
import me.climbingti.climbingtrainer.hangboard.addhangboard.HangboardLayoutViewPagerLayoutFragment;
import me.climbingti.climbingtrainer.common.Collection;

/**
 * Created by Aleksi on 28.12.2015.
 * in me.climbingti.climbingtrainer.Hangboard
 */
public class HangboardCardLayoutPagerAdapter extends FragmentStatePagerAdapter {

    private Collection hangArray;

    public HangboardCardLayoutPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public HangboardCardLayoutPagerAdapter(FragmentManager fm, Collection hangArray) {
        super(fm);
        this.hangArray = hangArray;
    }

    @Override
    public Fragment getItem(int position) {
        return HangboardLayoutViewPagerLayoutFragment.newInstance((HangboardLayout)hangArray.get(position));
    }

    @Override
    public int getCount() {
        return hangArray.size();
    }

}