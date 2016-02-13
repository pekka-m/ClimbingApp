package me.climbingti.climbingtrainer.mainview.bouldering;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;


import java.util.Date;

import me.climbingti.climbingtrainer.R;
import me.climbingti.climbingtrainer.bouldering.ClimbPresenter;
import me.climbingti.climbingtrainer.common.Collection;
import me.climbingti.climbingtrainer.common.Injection;

/**
 * Created by Aleksi on 14.10.2015.
 */
public class BoulderingFragment extends Fragment implements MainViewBoulderingContract.View{

    private static final String ARG_SECTION_NUMBER = "section number";
    private MainViewBoulderingContract.Presenter presenter;
    private ExpandableListView listView;

    public static BoulderingFragment newInstance(int sectionNumber) {
        BoulderingFragment fragment = new BoulderingFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_activity_fragment_list, container, false);
        listView = (ExpandableListView) rootView.findViewById(R.id.mainActivityFragment_expandableListView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = new MainViewBoulderingPresenter(Injection.createStatisticsInteractor(getContext()), this);
        presenter.loadBoulderingSends();
    }

    @Override
    public void showBoulderingSends(ArrayMap<Date, Collection> boulderingSends) {
        ClimbDataAdapter adapter = new ClimbDataAdapter(getContext(), boulderingSends);
        listView.setAdapter(adapter);

        //expand groups by default
        for (int i = 0; i < adapter.getGroupCount(); i++) {
            listView.expandGroup(i);
        }
    }
}