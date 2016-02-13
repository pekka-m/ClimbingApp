package me.climbingti.climbingtrainer.mainview.hanbgoarding;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.support.v4.util.ArrayMap;


import java.util.Date;

import me.climbingti.climbingtrainer.common.Injection;
import me.climbingti.climbingtrainer.R;
import me.climbingti.climbingtrainer.common.Collection;


/**
 * Created by Aleksi on 14.10.2015.
 */
public class HangboardFragment extends Fragment implements MainViewHangboardContract.View {

    private static final String ARG_SECTION_NUMBER = "section number";
    private MainViewHangboardContract.Presenter presenter;
    private ExpandableListView listView;

    public static HangboardFragment newInstance(int sectionNumber) {
        HangboardFragment fragment = new HangboardFragment();
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
        presenter = new MainviewHangboardPresenter(Injection.createStatisticsInteractor(getContext()), this);
        presenter.loadHangboardReps();
    }

    @Override
    public void showHangboardReps(ArrayMap<Date, Collection> hangboardReps) {
        HangboardDataAdapter adapter = new HangboardDataAdapter(getContext(), hangboardReps);
        listView.setAdapter(adapter);

        //expand groups by default
        for (int i = 0; i < adapter.getGroupCount(); i++) {
            listView.expandGroup(i);
        }
    }
}