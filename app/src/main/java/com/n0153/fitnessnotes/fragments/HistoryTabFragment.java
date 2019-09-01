package com.n0153.fitnessnotes.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.n0153.fitnessnotes.NewSetActivity;
import com.n0153.fitnessnotes.R;
import com.n0153.fitnessnotes.adapters.SetOptionsListAdapter;
import com.n0153.fitnessnotes.db_utils.DBhelper;
import com.n0153.fitnessnotes.db_utils.SetOptionsData;

import java.util.ArrayList;
import java.util.List;


public class HistoryTabFragment extends Fragment {

    private DBhelper dBhelper;
    private String name;
    private String type;
    private ListView mainListView;
    private String LOG_TAG = "History tab";

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "onCreate started");
        dBhelper = new DBhelper(getContext());
        name = ((NewSetActivity) getActivity()).getLabel();
        type = dBhelper.getExeriseType(name);


        Log.d(LOG_TAG, "onCreate Finished");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(LOG_TAG, "onCreateView Started");

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_history_tab, container, false);
        mainListView = v.findViewById(R.id.historyList);

        updateMainlist();
        Log.d(LOG_TAG, "onCreateView finished");

        return v;

    }


    private void updateMainlist() {

        ArrayList<SetOptionsData> setOptionsList = dBhelper.getSetOptionsList(name);

        SetOptionsListAdapter adapter = new SetOptionsListAdapter(getContext(), setOptionsList, type);
        mainListView.setAdapter(adapter);

    }

}



