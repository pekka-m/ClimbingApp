package me.climbingti.climbingtrainer.bouldering.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import me.climbingti.climbingtrainer.R;
import me.climbingti.climbingtrainer.bouldering.ClimbPresenter;
import me.climbingti.climbingtrainer.bouldering.ClimbView;
import me.climbingti.climbingtrainer.common.Collection;
import me.climbingti.climbingtrainer.common.Injection;

/**
 * Created by H3173 on 19.11.2015.
 */
public class BoulderingDetailFragment extends Fragment implements BoulderingDetailContract.View{

    private static final String ARG_SECTION_NUMBER = "section number";
    private static final String PRACTICE_ID= "practice_id";
    private BoulderingDetailContract.Presenter presenter;
    private ListView listView;

    public static BoulderingDetailFragment newInstance(int sectionNumber, int practiceId) {
        BoulderingDetailFragment fragment = new BoulderingDetailFragment();
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
        presenter = new BoulderingDetailPresenter(Injection.createClimbInteractor(getContext()), this);
        presenter.loadBoulderingSends(getPracticeIdFromExtras());
    }

    @Override
    public void showBoulderingSends(Collection boulderingSends) {
        BoulderingDetailDataAdapter adapter = new BoulderingDetailDataAdapter(getContext(), boulderingSends);
        listView.setAdapter(adapter);

    }

    private int getPracticeIdFromExtras(){
        Bundle bundle = getArguments();
        return bundle.getInt(PRACTICE_ID);
    }
}