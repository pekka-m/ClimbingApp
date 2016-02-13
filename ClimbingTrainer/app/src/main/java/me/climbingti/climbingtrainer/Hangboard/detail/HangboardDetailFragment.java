package me.climbingti.climbingtrainer.hangboard.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import me.climbingti.climbingtrainer.common.Injection;
import me.climbingti.climbingtrainer.hangboard.addhangboard.AddHangboardPresenter;
import me.climbingti.climbingtrainer.R;
import me.climbingti.climbingtrainer.common.Collection;
import me.climbingti.climbingtrainer.hangboard.addhangboard.HangboardView;


/**
 * Created by Pekka on 17.11.2015.
 */
public class HangboardDetailFragment extends Fragment implements HangboardDetailContract.View{

    private static final String ARG_SECTION_NUMBER = "section number";
    private static final String PRACTICE_ID= "practice_id";
    private HangboardDetailContract.Presenter presenter;
    private ListView listView;

    public static HangboardDetailFragment newInstance(int sectionNumber, int practiceId) {
        HangboardDetailFragment fragment = new HangboardDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putInt(PRACTICE_ID, practiceId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.detail_fragment, container, false);
        listView = (ListView) rootView.findViewById(R.id.detailFragment_listView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = new HangboardDetailPresenter(Injection.createHangboardInteractor(getContext()), this);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.loadHangboardReps(getPracticeIdFromExtras());
    }

    @Override
    public void showHangboardReps(Collection reps) {
        HangboardDetailDataAdapter adapter = new HangboardDetailDataAdapter(getContext(), reps);
        listView.setAdapter(adapter);

    }

    private int getPracticeIdFromExtras(){
        Bundle bundle = getArguments();
        return bundle.getInt(PRACTICE_ID);
    }
}