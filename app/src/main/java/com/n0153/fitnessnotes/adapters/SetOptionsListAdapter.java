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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class SetOptionsListAdapter extends BaseAdapter {

    private Context context;
    private String datePattern = "YYYY-MM-dd";
    private String timePattern = "HH:mm:ss";
    private ArrayList<SetOptionsData> setsList;
    private ArrayList<String> datesList;
    private LayoutInflater layoutInflater;
    private String type;
    private SimpleDateFormat dateFormat;


    public SetOptionsListAdapter(Context context, ArrayList<SetOptionsData> setsList, String type) {
        dateFormat = new SimpleDateFormat(datePattern);
        this.context = context;
        this.setsList = setsList;
        datesList = getDatesList();
        this.type = type;
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

        setSetsRecyclerView(setsListView, dateString);

        return view;
    }


    //create list with unique dates
    private ArrayList<String> getDatesList() {
        ArrayList<String> dates = new ArrayList<>();
        Set<String> datesSet = new HashSet<>();

        int n = setsList.size();
        for (int i = 0; i < n; i++) {
            String date = dateFormat.format(setsList.get(i).getDate());

            datesSet.add(date);
        }
        dates.addAll(datesSet);

        //sort cards by date

        Collections.sort(dates, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                try {
                    dateFormat.parse(o2).compareTo(dateFormat.parse(o1));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });


        return dates;
    }



    //method which set RecyclerView for each card
    private void setSetsRecyclerView(RecyclerView recyclerView, String date) {
        ArrayList<String> setsInADayList = new ArrayList<>();
        int n = setsList.size();

        for (int i = 0; i < n; i++) {
            String dateFormSets = dateFormat.format(setsList.get(i).getDate());
            if (date.equals(dateFormSets)) {
                int pos = setsInADayList.size() + 1;

                String stringItem = getSetString(pos, setsList.get(i));
                setsInADayList.add(stringItem);
            }

        }
        LinearLayoutManager llm = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(llm);

        CardRecyclerAdapter adapter = new CardRecyclerAdapter(context, setsInADayList);
        recyclerView.setAdapter(adapter);

    }


    // set text of the set in history card dependant of type
    private String getSetString(int position, SetOptionsData setOptionsData) {
        String result = position + ". ";
        String repsTime = "";
        SimpleDateFormat timeFormet = new SimpleDateFormat(timePattern);
        String time = timeFormet.format(setOptionsData.getDate());

        if (type.equals(context.getString(R.string.sp_weight_reps)) || type.equals(context.getString(R.string.sp_reps)))
            repsTime = context.getString(R.string.reps);

        if (type.equals(context.getString(R.string.sp_dist_time)) || type.equals(context.getString(R.string.sp_time)))
            repsTime = context.getString(R.string.time);


        //if type is weight and reps or dist and time
        if (type.equals(context.getString(R.string.sp_weight_reps)) || type.equals(context.getString(R.string.sp_dist_time))) {
            result += time + " " + setOptionsData.getWeightDistUnits() + ": " + setOptionsData.getWeightOrDist() +
                    " " + repsTime+ " " + setOptionsData.getRepsOrTime();
            if (!setOptionsData.getNote().equals("")) {
                result += "\n\t\t\t\t" + setOptionsData.getNote();
            }
        }

        //if type time or reps
        if (type.equals(context.getString(R.string.sp_reps)) || type.equals(context.getString(R.string.sp_time))) {
            result += time + " " + repsTime + " " + setOptionsData.getRepsOrTime();
            if (!setOptionsData.getNote().equals("")) {
                result += "\n\t\t\t\t" + setOptionsData.getNote();
            }
        }

        return result;
    }


}
