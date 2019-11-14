package com.n0153.fitnessnotes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.n0153.fitnessnotes.R;
import com.n0153.fitnessnotes.db_utils.models.SetOptionsDataModel;

import java.util.ArrayList;

public class TrackSetsAdapter extends BaseAdapter {

    Context context;
    ArrayList <SetOptionsDataModel> setsList;
    private LayoutInflater layoutInflater;

    public TrackSetsAdapter(Context context, ArrayList<SetOptionsDataModel> setsList) {
        this.context = context;
        this.setsList = setsList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return setsList.size();
    }

    @Override
    public Object getItem(int position) {
        return setsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.history_card_listraw, parent, false);
        String setInfo = SetsInADayAdapter.getSetString(position, setsList.get(position), context);

        ((TextView)convertView.findViewById(R.id.textViewSet)).setText(setInfo);
        ((TextView)convertView.findViewById(R.id.textViewSetDate)).setText(String.valueOf(setsList.get(position).getDate().getTime()));

        return convertView;
    }


}
