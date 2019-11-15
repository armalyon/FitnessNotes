package com.n0153.fitnessnotes.adapters;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.n0153.fitnessnotes.R;


public class SettingsListAdapter extends BaseAdapter {
    private String settingsArray [];
    private String settingsDescriptionArray [];
    Context context;
    LayoutInflater inflater;

    public SettingsListAdapter( Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        settingsArray = context.getResources().getStringArray(R.array.settingsArrayNames);
        settingsDescriptionArray = context.getResources().getStringArray(R.array.settingsArrayDescriptions);

    }

    @Override
    public int getCount() {
        return settingsArray.length;
    }

    @Override
    public Object getItem(int position) {
        return settingsArray[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.listraw_settings, parent, false);
        ((TextView)convertView.findViewById(R.id.settingsNameTextView)).setText(settingsArray[position]);
        ((TextView)convertView.findViewById(R.id.settingsDescriptionTextView)).setText(settingsDescriptionArray[position]);

        View dividerBottomn = convertView.findViewById(R.id.dividerThin12);
        if (position< settingsArray.length -1) dividerBottomn.setVisibility(View.GONE);

        return convertView;
    }
}
