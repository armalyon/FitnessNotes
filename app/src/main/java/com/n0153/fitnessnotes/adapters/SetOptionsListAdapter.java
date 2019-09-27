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
import com.n0153.fitnessnotes.Types.AdapterType;
import com.n0153.fitnessnotes.db_utils.models.SetDataModel;
import com.n0153.fitnessnotes.db_utils.models.SetOptionsDataModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SetOptionsListAdapter extends BaseAdapter {

    private Context context;
    public static final String DATE_PATTERN = "EEE, YYYY-MM-dd";
    public static final String TIME_PATTERN = "HH:mm:ss";
    private ArrayList<SetOptionsDataModel> setsList;
    private ArrayList<Date> datesList;
    private LayoutInflater layoutInflater;
    private String type;
    private SimpleDateFormat dateFormat;
    private String exercise;


    public SetOptionsListAdapter(Context context, ArrayList<SetOptionsDataModel> setsList, String type, String exercise) {
        dateFormat = new SimpleDateFormat(DATE_PATTERN);
        this.context = context;
        this.setsList = setsList;
        datesList = getDatesList();
        this.type = type;
        this.exercise = exercise;
        layoutInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return datesList.size();
    }

    @Override
    public Date getItem(int position) {
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

        String dateString = dateFormat.format(getItem(position));
        ((TextView) view.findViewById(R.id.textViewDate)).setText(dateString);

        RecyclerView setsListView = view.findViewById(R.id.setsListView);

        setSetsRecyclerView(setsListView, dateString);

        return view;
    }


    //create list with unique dates
    private ArrayList<Date> getDatesList() {
        ArrayList<Date> dates = new ArrayList<>();
        Set<String> datesSet = new HashSet<>();

        int n = setsList.size();
        for (int i = 0; i < n; i++) {
            String date = dateFormat.format(setsList.get(i).getDate());
            boolean isAdded = false;
            isAdded = datesSet.add(date);
            if (isAdded) dates.add(setsList.get(i).getDate());

        }


        //sort cards by date

        Collections.sort(dates, new Comparator<Date>() {
            @Override
            public int compare(Date o1, Date o2) {

                return o2.compareTo(o1);
            }
        });


        return dates;
    }



    //method which set RecyclerView for each card
    private void setSetsRecyclerView(RecyclerView recyclerView, String stringDate) {

        //arraylist to transfer text and date for each set
        ArrayList<SetDataModel> setsInADayList = new ArrayList<>();
        int n = setsList.size();

        for (int i = 0; i < n; i++) {
            Date date = setsList.get(i).getDate();
            String dateFormSets = dateFormat.format(date);
            if (stringDate.equals(dateFormSets)) {
                int pos = setsInADayList.size() + 1;

                String stringItem = getSetString(pos, setsList.get(i));
                setsInADayList.add(new SetDataModel(exercise, stringItem, date));
            }

        }
        LinearLayoutManager llm = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(llm);

        CardRecyclerAdapter adapter = new CardRecyclerAdapter(context, setsInADayList, AdapterType.SET_OPTIONS_LIST);
        adapter.setOnItemClickListener(adapter.getItemClickListener());
        recyclerView.setAdapter(adapter);


    }


    // set text of the set in history card dependant of type
    private String getSetString(int position, SetOptionsDataModel setOptionsData) {
        String result = position + ". ";
        String repsTime = "";
        SimpleDateFormat timeFormet = new SimpleDateFormat(TIME_PATTERN);
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
