package com.n0153.fitnessnotes.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.n0153.fitnessnotes.db_utils.models.SetOptionsDataModel;

import java.util.ArrayList;

public class SetsInADayAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<SetOptionsDataModel> setsList;
    private ArrayList<ArrayList<SetOptionsDataModel>> exerciseSetsList;

    public SetsInADayAdapter(Context context, ArrayList<SetOptionsDataModel> setsList) {
        this.context = context;
        this.setsList = setsList;
        exerciseSetsList = getExerciseSetsList();
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
        return null;
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
                    }
                }
            }
        }


        return exercisesList;
    }

}