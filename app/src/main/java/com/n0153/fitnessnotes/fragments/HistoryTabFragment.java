package com.n0153.fitnessnotes.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.n0153.fitnessnotes.NewSetActivity;
import com.n0153.fitnessnotes.R;
import com.n0153.fitnessnotes.db_utils.DBhelper;
import com.n0153.fitnessnotes.db_utils.SetOptionsData;

import java.util.List;


public class HistoryTabFragment extends Fragment {

    DBhelper dBhelper;
    String name;
    String type;
    List<SetOptionsData> list;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dBhelper = new DBhelper(getContext());
        name = ((NewSetActivity)getActivity()).getLabel();
        type = dBhelper.getExeriseType(name);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_history_tab, container, false);



        return v;

    }







}



