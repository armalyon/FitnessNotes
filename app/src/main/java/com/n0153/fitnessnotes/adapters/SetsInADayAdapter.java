package com.n0153.fitnessnotes.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.n0153.fitnessnotes.R;
import com.n0153.fitnessnotes.Types.AdapterType;
import com.n0153.fitnessnotes.db_utils.DBhelper;
import com.n0153.fitnessnotes.db_utils.models.SetDataModel;
import com.n0153.fitnessnotes.db_utils.models.SetOptionsDataModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SetsInADayAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<SetOptionsDataModel> setsList;
    private ArrayList<ArrayList<SetOptionsDataModel>> exerciseSetsList;
    private LayoutInflater layoutInflater;
    private final String LOG_TAG = "Sets in a day adapter";

    public SetsInADayAdapter(Context context, ArrayList<SetOptionsDataModel> setsList) {
        this.context = context;
        this.setsList = setsList;
        exerciseSetsList = getExerciseSetsList();
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return exerciseSetsList.size();
    }

    @Override
    public Object getItem(int position) {
        return exerciseSetsList.get(position);
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
        String exerciseName = exerciseSetsList.get(position).get(0).getName();
        ((TextView) view.findViewById(R.id.textViewDate)).setText(exerciseName);
        RecyclerView recyclerView = view.findViewById(R.id.setsListView);

        setRecyclerView(position, recyclerView);

        return view;
    }


//method to get list of sets for particular exercises

    private ArrayList<ArrayList<SetOptionsDataModel>> getExerciseSetsList() {
        ArrayList<ArrayList<SetOptionsDataModel>> exercisesList = new ArrayList<>();
        int n = setsList.size();

        if (n > 0) {
            //list for each exercise car
            if (n <= 1) {

                exercisesList.add(setsList);
            } else {

                ArrayList<SetOptionsDataModel> cardList = new ArrayList<>();
                cardList.add(setsList.get(0));

                for (int i = 1; i < n; i++) {

                    if (setsList.get(i).getName().equals(cardList.get(0).getName()))
                        cardList.add(setsList.get(i));
                    else {

                        exercisesList.add(cardList);
                        cardList = new ArrayList<>();
                        cardList.add(setsList.get(i));
                    }
                }
                exercisesList.add(cardList);
            }
        }

        return exercisesList;
    }

    private void setRecyclerView(int position, RecyclerView recyclerView) {
        ArrayList<SetOptionsDataModel> setstOptionsList = exerciseSetsList.get(position);

        String name = setstOptionsList.get(0).getName();
        int n = setstOptionsList.size();
        ArrayList<SetDataModel> recyclerList = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            String setText = getSetString(i + 1, setstOptionsList.get(i));
            recyclerList.add(new SetDataModel(name, setText, setstOptionsList.get(i).getDate()));
        }

        LinearLayoutManager llm = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(llm);
        CardRecyclerAdapter adapter = new CardRecyclerAdapter(context, recyclerList, AdapterType.SETS_IN_A_DAY);
        adapter.setOnItemClickListener(adapter.getItemClickListener());
        recyclerView.setAdapter(adapter);


    }

    // set text of the set in history card dependant of type
    private String getSetString(int position, SetOptionsDataModel setOptionsData) {
        DBhelper dBhelper = new DBhelper(context);

        String type = dBhelper.getExeriseType(setOptionsData.getName());
        String result = position + ". ";
        String repsTime = "";
        SimpleDateFormat timeFormet = new SimpleDateFormat(SetOptionsListAdapter.TIME_PATTERN);
        String time = timeFormet.format(setOptionsData.getDate());

        if (type.equals(context.getString(R.string.sp_weight_reps)) || type.equals(context.getString(R.string.sp_reps)))
            repsTime = context.getString(R.string.reps);

        if (type.equals(context.getString(R.string.sp_dist_time)) || type.equals(context.getString(R.string.sp_time)))
            repsTime = context.getString(R.string.time);


        //if type is weight and reps or dist and time
        if (type.equals(context.getString(R.string.sp_weight_reps)) || type.equals(context.getString(R.string.sp_dist_time))) {
            result += time + " " + setOptionsData.getWeightDistUnits() + ": " + setOptionsData.getWeightOrDist() +
                    " " + repsTime + " " + setOptionsData.getRepsOrTime();
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