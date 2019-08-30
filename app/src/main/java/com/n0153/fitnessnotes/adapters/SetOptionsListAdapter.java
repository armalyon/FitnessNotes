package com.n0153.fitnessnotes.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.n0153.fitnessnotes.R;
import com.n0153.fitnessnotes.db_utils.SetOptionsData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SetOptionsListAdapter extends ArrayAdapter<SetOptionsData> {

    private Context context;
    String datePattern = "YYYY-MM-DD";
    ArrayList<SetOptionsData> itemsList;


    public SetOptionsListAdapter(Context context, int resource, ArrayList<SetOptionsData> itemsList) {
        super(context, resource);
        this.context = context;
        this.itemsList = itemsList;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = getView(position, convertView, parent);
        TextView dateTextView = v.findViewById(R.id.textViewDate);

        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);

        Date date = itemsList.get(position).getDate();
        String dateString = dateFormat.format(date);
        dateTextView.setText(dateString);

        return v;
    }
}
