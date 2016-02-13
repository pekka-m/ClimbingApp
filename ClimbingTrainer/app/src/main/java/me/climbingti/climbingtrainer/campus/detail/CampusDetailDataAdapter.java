package me.climbingti.climbingtrainer.campus.detail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import me.climbingti.climbingtrainer.R;
import me.climbingti.climbingtrainer.campus.CampusCollection;
import me.climbingti.climbingtrainer.campus.CampusEntity;
import me.climbingti.climbingtrainer.common.Collection;
import me.climbingti.climbingtrainer.util.DateConverter;

/**
 * Created by H4198 on 17.11.2015.
 */
public class CampusDetailDataAdapter extends BaseAdapter {
    private Context context;
    private CampusCollection campusEntities;
    private DateConverter dateConverter;

    public CampusDetailDataAdapter(Context context, Collection campusEntities) {
        this.context = context;
        this.campusEntities = (CampusCollection) campusEntities;
        this.dateConverter = new DateConverter();
    }

    @Override
    public int getCount() {
        return campusEntities.size();
    }

    @Override
    public Object getItem(int position) {
        return campusEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return campusEntities.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_row, null);
        }

        TextView time = (TextView) convertView.findViewById(R.id.listRow_textView_time);
        TextView steps = (TextView) convertView.findViewById(R.id.listRow_textView_value);

        CampusEntity entity = (CampusEntity) campusEntities.get(position);
        time.setText(dateConverter.convertIntoHoursMinutes(entity.getDate()));
        steps.setText(Integer.toString(entity.getSteps()));

        return convertView;
    }
}