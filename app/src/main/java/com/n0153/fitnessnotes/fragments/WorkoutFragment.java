package com.n0153.fitnessnotes.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.n0153.fitnessnotes.R;
import com.n0153.fitnessnotes.adapters.SetsInADayAdapter;
import com.n0153.fitnessnotes.db_utils.DBhelper;
import com.n0153.fitnessnotes.db_utils.models.SetOptionsDataModel;
import com.n0153.fitnessnotes.interfaces.DateGettable;

import java.util.ArrayList;



public class WorkoutFragment extends Fragment {

    private long dateLong;


    DBhelper dBhelper;
    ListView mainListView;
    String LOG_TAG = "Workout Fragment";


    public WorkoutFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_workout, container, false);

        mainListView = v.findViewById(R.id.woFragmentListview);

        return v;
    }


    @Override
    public void onStart() {
        super.onStart();
        dBhelper = new DBhelper(getContext());
        dateLong = ((DateGettable)getActivity()).getLongDate();

        updateMainListView();
    }

    @Override
    public void onResume() {
        super.onResume();
        updateMainListView();
    }

    public void updateMainListView(){
        ArrayList<SetOptionsDataModel> setsInADaylist = dBhelper.getSetsByDay(dateLong);
        SetsInADayAdapter adapter = new SetsInADayAdapter(getContext(), setsInADaylist);
        mainListView.setAdapter(adapter);
    }


}
