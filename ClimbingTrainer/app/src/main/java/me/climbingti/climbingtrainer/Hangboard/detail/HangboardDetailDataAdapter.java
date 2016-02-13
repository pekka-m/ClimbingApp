package me.climbingti.climbingtrainer.hangboard.detail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import me.climbingti.climbingtrainer.hangboard.Domain.HangboardCollection;
import me.climbingti.climbingtrainer.hangboard.Domain.HangboardEntity;
import me.climbingti.climbingtrainer.R;
import me.climbingti.climbingtrainer.common.Collection;
import me.climbingti.climbingtrainer.util.DateConverter;

/**
 * Created by H3173 on 17.11.2015.
 */
public class HangboardDetailDataAdapter extends BaseAdapter {

    private Context context;
    private HangboardCollection collection;
    private DateConverter dateConverter;

    public HangboardDetailDataAdapter(Context context, Collection collection) {
        this.context = context;
        this.collection = (HangboardCollection) collection;
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

        TextView datetime = (TextView) convertView.findViewById(R.id.listRow_textView_time);
        TextView time = (TextView) convertView.findViewById(R.id.listRow_textView_value);

        HangboardEntity entity = (HangboardEntity) collection.get(position);

        datetime.setText(dateConverter.convertIntoHoursMinutes(entity.getDate()));
        time.setText(Integer.toString(entity.getHangTime()));

        return convertView;
    }
}
