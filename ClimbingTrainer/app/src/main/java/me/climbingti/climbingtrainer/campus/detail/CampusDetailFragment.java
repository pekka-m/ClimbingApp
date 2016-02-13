package me.climbingti.climbingtrainer.campus.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import me.climbingti.climbingtrainer.R;
import me.climbingti.climbingtrainer.common.Collection;
import me.climbingti.climbingtrainer.common.Injection;

/**
 * Created by Aleksi on 14.10.2015.
 */
public class CampusDetailFragment extends Fragment implements CampusdetailContract.View {

    private static final String ARG_SECTION_NUMBER = "section number";
    private static final String PRACTICE_ID= "practice_id";
    private CampusdetailContract.Presenter presenter;
    private ListView listView;

    public static CampusDetailFragment newInstance(int sectionNumber, int practiceId) {
        CampusDetailFragment fragment = new CampusDetailFragment();
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
        presenter = new CampusDetailPresenter(Injection.createCampusInteractor(getContext()), this);
        presenter.loadCampusReps(getPracticeIdFromExtras());
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void showCampusReps(Collection campusReps) {
        CampusDetailDataAdapter adapter = new CampusDetailDataAdapter(getContext(), campusReps);
        listView.setAdapter(adapter);
    }

    private int getPracticeIdFromExtras(){
        Bundle bundle = getArguments();
        return bundle.getInt(PRACTICE_ID);
    }
}