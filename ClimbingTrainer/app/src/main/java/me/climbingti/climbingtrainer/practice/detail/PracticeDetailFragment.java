package me.climbingti.climbingtrainer.practice.detail;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.climbingti.climbingtrainer.R;
import me.climbingti.climbingtrainer.common.Injection;
import me.climbingti.climbingtrainer.practice.PracticeOverViewEntity;

/**
 * Created by H3173 on 19.11.2015.
 */
public class PracticeDetailFragment extends Fragment implements PracticeDetailContract.View {

    private static final String ARG_SECTION_NUMBER = "section number";
    private static final String ARG_PRACTICE_ID = "practice_id";
    private PracticeDetailContract.Presenter presenter;
    TextView textView;
    View rootView;

    public static PracticeDetailFragment newInstance(int sectionNumber, int id) {
        PracticeDetailFragment fragment = new PracticeDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putInt(ARG_PRACTICE_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.practice_detail_fragment, container, false);
        return rootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        presenter = new PracticeDetailPresenter(Injection.createStatisticsInteractor(getContext()), this);
        presenter.loadPracticeDetails(getPracticeIdFromArguments());
    }

    @Override
    public void showPracticeDetails(PracticeOverViewEntity practiceDetails) {
        textView = (TextView) this.rootView.findViewById(R.id.practiceDetailsFragment_TextView_date);
        textView.setText(practiceDetails.getDate().toString());

        textView = (TextView) this.rootView.findViewById(R.id.practiceDetailsFragment_textView_sends_value);
        textView.setText(Integer.toString(practiceDetails.getNumberofSends()));

        textView = (TextView) this.rootView.findViewById(R.id.practiceDetailsFragment_textView_maxGrade_value);
        textView.setText(practiceDetails.getMaxGrade());

        textView = (TextView) this.rootView.findViewById(R.id.practiceDetailsFragment_textView_projectingGrades_value);
        textView.setText(practiceDetails.getProjectingGrades()[0]);

        textView = (TextView) this.rootView.findViewById(R.id.practiceDetailsFragment_textView_totalHangTime_value);
        textView.setText(Integer.toString(practiceDetails.getTotalHangtime()));

        textView = (TextView) this.rootView.findViewById(R.id.practiceDetailsFragment_textView_campusSteps_value);
        textView.setText(Integer.toString(practiceDetails.getCampusSteps()));

        textView = (TextView) this.rootView.findViewById(R.id.practiceDetailsFragment_textView_campusDistance_value);
        textView.setText(Double.toString(practiceDetails.getCampusDistance()));
    }

    private int getPracticeIdFromArguments() {
        return getArguments().getInt(ARG_PRACTICE_ID, 1);
    }
}