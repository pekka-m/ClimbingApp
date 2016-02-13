package me.climbingti.climbingtrainer.mainview.campus;

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
import me.climbingti.climbingtrainer.campus.addcampus.AddCampusContract;
import me.climbingti.climbingtrainer.campus.addcampus.CampusPresenter;
import me.climbingti.climbingtrainer.common.Collection;
import me.climbingti.climbingtrainer.common.Injection;

/**
 * Created by Aleksi on 14.10.2015.
 */
public class CampusFragment extends Fragment implements MainViewCampusContract.View {

    private static final String ARG_SECTION_NUMBER = "section number";
    private MainViewCampusContract.Presenter presenter;
    private ExpandableListView listView;

    public static CampusFragment newInstance(int sectionNumber) {
        CampusFragment fragment = new CampusFragment();
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
        presenter = new MainViewCampusPresenter(Injection.createStatisticsInteractor(getContext()),this);
        presenter.loadCampusReps();
    }


    @Override
    public void showCampusReps(ArrayMap<Date, Collection> campusReps) {
        CampusDataAdapter adapter = new CampusDataAdapter(getContext(), campusReps);
        listView.setAdapter(adapter);

        //expand groups by default
        for (int i = 0; i < adapter.getGroupCount(); i++) {
            listView.expandGroup(i);
        }
    }
}