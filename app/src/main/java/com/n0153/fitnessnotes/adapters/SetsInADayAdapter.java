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

public class SetsInADayAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<SetOptionsDataModel> setsList;
    private ArrayList<ArrayList<SetOptionsDataModel>> exerciseSetsList;
    private LayoutInflater layoutInflater;

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

        return view;
    }


//method to get list of sets for particular exercise

    private ArrayList<ArrayList<SetOptionsDataModel>> getExerciseSetsList() {
        ArrayList<ArrayList<SetOptionsDataModel>> exercisesList = new ArrayList<>();
        int n = setsList.size();
        if (n > 0) {
            //list for each exercise card
            if (n==1){exercisesList.add(setsList);}
            else {
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
            }
        }


        return exercisesList;
    }

}