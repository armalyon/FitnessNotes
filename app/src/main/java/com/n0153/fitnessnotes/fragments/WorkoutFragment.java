package com.n0153.fitnessnotes.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.n0153.fitnessnotes.R;
import com.n0153.fitnessnotes.ViewWorkoutActivity;
import com.n0153.fitnessnotes.adapters.SetsInADayAdapter;
import com.n0153.fitnessnotes.db_utils.DBhelper;
import com.n0153.fitnessnotes.db_utils.models.SetOptionsDataModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class WorkoutFragment extends Fragment {

    final String headerDatePattern = "yyyy, dd MMMM,  E";
    private long dateLong;

    TextView headerTextView;
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

        headerTextView = v.findViewById(R.id.woFragmentHeader);
        mainListView = v.findViewById(R.id.woFragmentListview);

        return v;
    }


    @Override
    public void onStart() {
        super.onStart();
        dBhelper = new DBhelper(getContext());
        dateLong = ((ViewWorkoutActivity)getActivity()).getDateLong();
        SimpleDateFormat sdf = new SimpleDateFormat(headerDatePattern);
        Date date = new Date(dateLong);
        headerTextView.setText(sdf.format(date));

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
