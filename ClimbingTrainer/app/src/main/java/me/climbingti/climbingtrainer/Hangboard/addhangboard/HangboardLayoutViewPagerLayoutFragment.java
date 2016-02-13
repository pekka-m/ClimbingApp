package me.climbingti.climbingtrainer.hangboard.addhangboard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.climbingti.climbingtrainer.hangboard.Domain.HangboardLayout;
import me.climbingti.climbingtrainer.R;

///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link HangboardLayoutViewPagerLayoutFragment.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link HangboardLayoutViewPagerLayoutFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class HangboardLayoutViewPagerLayoutFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String HANG_TIME= "hang_time";
    private static final String REST_TIME = "rest_time";
    private static final String REPS     = "reps";



//    private OnFragmentInteractionListener mListener;

    public HangboardLayoutViewPagerLayoutFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment HangboardLayoutViewPagerLayoutFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HangboardLayoutViewPagerLayoutFragment newInstance(HangboardLayout hangboardLayout) {
        HangboardLayoutViewPagerLayoutFragment fragment = new HangboardLayoutViewPagerLayoutFragment();
        Bundle args = new Bundle();
        args.putInt(HANG_TIME, hangboardLayout.getHangTime());
        args.putInt(REST_TIME, hangboardLayout.getRestTime());
        args.putInt(REPS, hangboardLayout.getReps());
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hangboard_layout_view_pager_layout, container, false);
        Bundle args = getArguments();
        ((TextView) rootView.findViewById(R.id.hang_time_value)).setText(
                Integer.toString(args.getInt(HANG_TIME))
        );
        ((TextView) rootView.findViewById(R.id.rest_time_value)).setText(
                Integer.toString(args.getInt(REST_TIME))
        );
        ((TextView) rootView.findViewById(R.id.reps_value)).setText(
                Integer.toString(args.getInt(REPS))
        );

        return rootView;    }

    public HangboardLayout getData(){
        HangboardLayout layout = new HangboardLayout();
        layout.setHangTime(ArgumenttiShitti(HANG_TIME));
        layout.setRestTime(ArgumenttiShitti(REST_TIME));
        layout.setReps(ArgumenttiShitti(REPS));
        return layout;
    }

    private int ArgumenttiShitti(String key) {
        Bundle args = getArguments();
        return  args.getInt(key);
    }

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p/>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
