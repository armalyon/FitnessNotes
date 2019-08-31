package com.n0153.fitnessnotes.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.n0153.fitnessnotes.R;
import com.n0153.fitnessnotes.db_utils.SetOptionsData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SetOptionsListAdapter extends BaseAdapter {

    private Context context;
    private String datePattern = "YYYY-MM-DD";
    private ArrayList<SetOptionsData> itemsList;
    private LayoutInflater layoutInflater;


    public SetOptionsListAdapter(Context context, ArrayList<SetOptionsData> itemsList) {
        this.context = context;
        this.itemsList = itemsList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return itemsList.size();
    }

    @Override
    public SetOptionsData getItem(int position) {
        return itemsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.history_listraw_layout, parent, false);
        }
       SetOptionsData setOptionsData  = getItem(position);
        String date = setOptionsData.getDate().toString();

        ((TextView)view.findViewById(R.id.textViewDate)).setText(date);



        return view;
    }


}
