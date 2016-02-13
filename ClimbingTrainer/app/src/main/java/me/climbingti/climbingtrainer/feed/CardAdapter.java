package me.climbingti.climbingtrainer.feed;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.climbingti.climbingtrainer.R;
import me.climbingti.climbingtrainer.common.Collection;
import me.climbingti.climbingtrainer.common.Navigator;
import me.climbingti.climbingtrainer.practice.PracticeOverViewEntity;

/**
 * Created by Aleksi on 4.11.2015.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private Collection practiceCollection;
    private Navigator navigator;
    private Context context;

    public CardAdapter(Collection practiceCollection, Context context){
        this.practiceCollection= practiceCollection;
        this.navigator = new Navigator();
        this.context = context;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_activity_card, parent, false);
        return new CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CardViewHolder viewHolder, int position) {
        Log.d("feed", "lisätään jokaseen korttiin dataa");


        PracticeOverViewEntity dayCard = (PracticeOverViewEntity) practiceCollection.get(position);
        viewHolder.numberOfSendsToday.setText(Integer.toString(dayCard.getNumberofSends()));
        Log.d("joo", "onBindViewHolder: max grade " + dayCard.getMaxGrade());
        viewHolder.maxGradeClimbedToday.setText(dayCard.getMaxGrade());
        viewHolder.totalHangtimeToday.setText(Integer.toString(dayCard.getTotalHangtime()));
        viewHolder.totalStepsToday.setText(Integer.toString(dayCard.getCampusSteps()));
        viewHolder.button.setOnClickListener(onClickListener(dayCard));
        Log.d("joo", "onBindViewHolder: " + dayCard.getPracticeId());
    }

    private View.OnClickListener onClickListener(final PracticeOverViewEntity dayCard){
       return new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               navigator.navigateToPracticeDetailActivity(context, Integer.parseInt(Long.toString(dayCard.getPracticeId())));
           }
       };
    }

    @Override
    public int getItemCount() {
        return practiceCollection.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder{
        protected TextView numberOfSendsToday;
        protected TextView maxGradeClimbedToday;
        protected TextView totalHangtimeToday;
        protected TextView totalStepsToday;
        protected TextView button;

        public CardViewHolder(View v){
            super(v);
            numberOfSendsToday = (TextView) v.findViewById(R.id.card_send_amount);
            maxGradeClimbedToday = (TextView) v.findViewById(R.id.card_max_grade_value);
            totalHangtimeToday = (TextView) v.findViewById(R.id.card_hangtime_amount);
            totalStepsToday = (TextView) v.findViewById(R.id.card_steps_amount);
            button = (TextView) v.findViewById(R.id.card_details_button);
        }


    }
}