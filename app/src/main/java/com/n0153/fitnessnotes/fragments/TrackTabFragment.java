package com.n0153.fitnessnotes.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.n0153.fitnessnotes.ActivityExercises;
import com.n0153.fitnessnotes.NewSetActivity;
import com.n0153.fitnessnotes.R;
import com.n0153.fitnessnotes.db_utils.DBhelper;
import com.n0153.fitnessnotes.db_utils.ExOptionsData;


public class TrackTabFragment extends Fragment {

    private DBhelper dBhelper;
    TextView unitsTextView, amountTextView;
    private final String LOG_TAG = "Track Tab";


    public TrackTabFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dBhelper = new DBhelper(getContext());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_track_tab, container, false);
        unitsTextView = v.findViewById(R.id.unitsTextView);
        amountTextView = v.findViewById(R.id.amountTextView);
        setTextFields();
        return v;


    }


    private void setTextFields() {

        String exercise = ((NewSetActivity) getActivity()).getLabel();
        Log.d(LOG_TAG, exercise);
        ExOptionsData exOptionsData = dBhelper.getExOptionsData(exercise);
        unitsTextView.setText(exOptionsData.getUnits() + ": ");
        String type = exOptionsData.getType();

        String amountOf = null;

        if (type.equals(getString(R.string.sp_weight_reps)))
            amountOf = getString(R.string.item_reps);
        else amountOf = getString(R.string.item_time);

        amountTextView.setText(amountOf);
    }

}
