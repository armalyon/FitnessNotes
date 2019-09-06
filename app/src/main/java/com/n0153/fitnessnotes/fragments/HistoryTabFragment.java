package com.n0153.fitnessnotes.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.n0153.fitnessnotes.NewSetActivity;
import com.n0153.fitnessnotes.R;
import com.n0153.fitnessnotes.adapters.SetOptionsListAdapter;
import com.n0153.fitnessnotes.db_utils.DBhelper;
import com.n0153.fitnessnotes.db_utils.SetOptionsData;
import com.n0153.fitnessnotes.dialogs.ViewWorkoutFragment;

import java.util.ArrayList;
import java.util.List;


public class HistoryTabFragment extends Fragment implements AdapterView.OnItemLongClickListener {

    private DBhelper dBhelper;
    private String name;
    private String type;
    private ListView mainListView;
    private ViewWorkoutFragment viewWorkoutDialog;
    private String LOG_TAG = "History tab";
    private String dialogViewWorkoutTag = "start dialog view workout";

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "onCreate started");
        dBhelper = new DBhelper(getContext());
        name = ((NewSetActivity) getActivity()).getLabel();
        type = dBhelper.getExeriseType(name);
        viewWorkoutDialog = new ViewWorkoutFragment();

        Log.d(LOG_TAG, "onCreate Finished");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(LOG_TAG, "onCreateView Started");

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_history_tab, container, false);
        mainListView = v.findViewById(R.id.historyList);
        mainListView.setOnItemLongClickListener(this);

        updateMainlist();
        Log.d(LOG_TAG, "onCreateView finished");

        return v;

    }


    protected void updateMainlist() {

        ArrayList<SetOptionsData> setOptionsList = dBhelper.getSetOptionsList(name);

        SetOptionsListAdapter adapter = new SetOptionsListAdapter(getContext(), setOptionsList, type);
        mainListView.setAdapter(adapter);


    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        viewWorkoutDialog.show(getFragmentManager(), dialogViewWorkoutTag);


        return false;
    }
}



