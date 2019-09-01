package com.n0153.fitnessnotes.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.n0153.fitnessnotes.R;
import com.n0153.fitnessnotes.db_utils.SetOptionsData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SetOptionsListAdapter extends BaseAdapter {

    private Context context;
    private String datePattern = "YYYY-MM-dd";
    private ArrayList<SetOptionsData> setsList;
    private ArrayList<String> datesList;
    private LayoutInflater layoutInflater;


    public SetOptionsListAdapter(Context context, ArrayList<SetOptionsData> setsList) {
        this.context = context;
        this.setsList = setsList;
        datesList = getDatesList();
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return datesList.size();
    }

    @Override
    public String getItem(int position) {
        return datesList.get(position);
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

        String dateString = getItem(position);
        ((TextView) view.findViewById(R.id.textViewDate)).setText(dateString);

        RecyclerView setsListView = view.findViewById(R.id.setsListView);

        setSetsListView(setsListView, dateString);

        return view;
    }


    //create list with unique dates
    private ArrayList<String> getDatesList() {
        ArrayList<String> dates = new ArrayList<>();
        Set<String> datesSet = new HashSet<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
        int n = setsList.size();
        for (int i = 0; i < n; i++) {
            String date = dateFormat.format(setsList.get(i).getDate());
            datesSet.add(date);
        }
        dates.addAll(datesSet);

        return dates;
    }

    private void setSetsListView(RecyclerView recyclerView, String date) {
        ArrayList<String> setsInADayList = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
        int n = setsList.size();

        for (int i = 0; i < n; i++) {
            String dateFormSets = dateFormat.format(setsList.get(i).getDate());
            if (date.equals(dateFormSets)) {
                //TEMP TO TEST
                String stringItem = "Weight/Dist: " + setsList.get(i).getWeightOrDist() +
                        " Reps/Time: " + setsList.get(i).getRepsOrTime() + "\n" +
                        " Note: " + setsList.get(i).getNote();
                setsInADayList.add(stringItem);
            }


        }
        LinearLayoutManager llm = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(llm);

        CardRecyclerAdapter adapter = new CardRecyclerAdapter(context, setsInADayList);
        recyclerView.setAdapter(adapter);

    }


}
