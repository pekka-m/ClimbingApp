package me.climbingti.climbingtrainer.feed;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.climbingti.climbingtrainer.R;
import me.climbingti.climbingtrainer.common.Collection;
import me.climbingti.climbingtrainer.common.Injection;
import me.climbingti.climbingtrainer.common.Navigator;


/**
 * Created by Aleksi on 4.11.2015.
 */
public class FeedFragment extends Fragment implements FeedContract.View {

    private static final String ARG_SECTION_NUMBER = "section number";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;
    private Context context;

    private FeedContract.Presenter presenter;

    public static FeedFragment newInstance(int sectionNumber){
        FeedFragment fragment = new FeedFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                                          ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.feed_main_activity_fragment, container, false);
        this.context = getActivity();
        setupRecyclerView(rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = new FeedPresenter(Injection.createStatisticsInteractor(getContext()),
                Injection.createSharedPrefManager(getContext()), this);
        try {
            Log.d("feed", "kutsutaan presenterin getDataa");

            presenter.loadCards();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showCards(Collection entities) {
        Log.d("feed", "lisätään adapteriin dataa");
        Log.d("feed", "datan koko: " +entities.size());

        adapter = new CardAdapter(entities,context);
        recyclerView.setAdapter(adapter);
    }

    private void setupRecyclerView(View rootView){
        recyclerView = (RecyclerView) rootView.findViewById(R.id.feedMainActivityFragment_recyclerView);
        recyclerView.setHasFixedSize(true);
        manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
    }

}