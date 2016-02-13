package me.climbingti.climbingtrainer.practice.browser;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import me.climbingti.climbingtrainer.R;
import me.climbingti.climbingtrainer.common.Collection;
import me.climbingti.climbingtrainer.common.Injection;
import me.climbingti.climbingtrainer.feed.CardAdapter;

/**
 * Created by H3173 on 19.11.2015.
 */
public class PracticeBrowserActivity extends AppCompatActivity implements PracticeBrowserContract.View {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;
    private PracticeBrowserContract.Presenter presenter;
    private Context context;

    public static Intent getCallingIntent(Context context){
        Intent callingIntent = new Intent(context, PracticeBrowserActivity.class);
        return callingIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this;

        setContentView(R.layout.feed_main_activity_fragment);
        setupRecyclerView();

        presenter = new PracticeBrowserPresenter(Injection.createStatisticsInteractor(this), this);
        presenter.loadAllPracticeSessions();

    }

    @Override
    public void showAllPracticeSessions(Collection practiceSessions) {
        adapter = new CardAdapter(practiceSessions,context);
        recyclerView.setAdapter(adapter);
    }

    private void setupRecyclerView(){
        recyclerView = (RecyclerView) findViewById(R.id.feedMainActivityFragment_recyclerView);
        recyclerView.setHasFixedSize(true);
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
    }

}