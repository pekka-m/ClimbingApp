package me.climbingti.climbingtrainer.bouldering.detail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import me.climbingti.climbingtrainer.R;

import me.climbingti.climbingtrainer.bouldering.ClimbCollection;
import me.climbingti.climbingtrainer.bouldering.ClimbEntity;
import me.climbingti.climbingtrainer.common.Collection;
import me.climbingti.climbingtrainer.util.DateConverter;

/**
 * Created by H4198 on 19.11.2015.
 */
public class BoulderingDetailDataAdapter extends BaseAdapter {
    private Context context;
    private ClimbCollection collection;
    private DateConverter dateConverter;

    public BoulderingDetailDataAdapter(Context context, Collection climbEntities) {
        this.context = context;
        this.collection = (ClimbCollection) climbEntities;
        this.dateConverter = new DateConverter();
    }

    @Override
    public int getCount() {
        return collection.size();
    }

    @Override
    public Object getItem(int position) {
        return collection.get(position);
    }

    @Override
    public long getItemId(int position) {
        return collection.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_row, null);
        }

        TextView time = (TextView) convertView.findViewById(R.id.listRow_textView_time);
        TextView grade = (TextView) convertView.findViewById(R.id.listRow_textView_value);

        ClimbEntity entity = (ClimbEntity) collection.get(position);
        time.setText(dateConverter.convertIntoHoursMinutes(entity.getDate()));
        grade.setText(entity.getGrade());

        return convertView;
    }
}