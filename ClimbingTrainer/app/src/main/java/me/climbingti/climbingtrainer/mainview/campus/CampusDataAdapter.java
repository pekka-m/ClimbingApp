package me.climbingti.climbingtrainer.mainview.campus;

import android.content.Context;
import android.support.v4.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.Date;

import me.climbingti.climbingtrainer.R;
import me.climbingti.climbingtrainer.campus.CampusCollection;
import me.climbingti.climbingtrainer.campus.CampusEntity;
import me.climbingti.climbingtrainer.common.Collection;
import me.climbingti.climbingtrainer.util.DateConverter;

/**
 * Created by Pekka Melgin on 25.10.2015.
 */
public class CampusDataAdapter extends BaseExpandableListAdapter {

    private ArrayMap<Date, Collection> collectionArrayMap;
    private Context context;

    private DateConverter dateConverter;

    public CampusDataAdapter(Context context, ArrayMap<Date, Collection> collectionArrayMap) {
        this.context = context;
        this.collectionArrayMap = collectionArrayMap;
        dateConverter = new DateConverter();
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_row_title, null);
        }

        TextView date = (TextView) convertView.findViewById(R.id.listRowTitle_textView_title);
        date.setText(dateConverter.convertIntoDayMonth(collectionArrayMap.keyAt(groupPosition)));

        //hide expand-collapse-arrow
        ExpandableListView elv = (ExpandableListView) parent;
        elv.setGroupIndicator(null);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        CampusCollection child = (CampusCollection) collectionArrayMap.valueAt(groupPosition);
        CampusEntity entity = (CampusEntity) child.get(childPosition);

        TextView time;
        TextView practice;
        TextView steps;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_row, null);
        }
        time = (TextView)  convertView.findViewById(R.id.listRow_textView_time);
        practice = (TextView) convertView.findViewById(R.id.listRow_textView_id);
        steps = (TextView)  convertView.findViewById(R.id.listRow_textView_value);

        time.setText(dateConverter.convertIntoHoursMinutes(child.get(childPosition).getDate()));
        practice.setText(Long.toString(child.get(childPosition).getPracticeId()));
        steps.setText(Integer.toString(entity.getSteps()));
        return convertView;
    }

    @Override
    public int getGroupCount() {
        try {
            return collectionArrayMap.size();
        } catch (NullPointerException e) {
            return 0;
        }
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return  collectionArrayMap.valueAt(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {return null;}
    @Override
    public Object getChild(int groupPosition, int childPosition) {return null;}
    @Override
    public long getGroupId(int groupPosition) {return 0;}
    @Override
    public long getChildId(int groupPosition, int childPosition) {return 0;}
    @Override
    public boolean hasStableIds() {return false;}
    @Override
    public void onGroupExpanded(int groupPosition){super.onGroupExpanded(groupPosition);}
    @Override
    public void onGroupCollapsed(int groupPosition){super.onGroupCollapsed(groupPosition);}
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {return false;}
}