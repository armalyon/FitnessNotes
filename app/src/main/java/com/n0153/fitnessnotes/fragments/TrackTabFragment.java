package com.n0153.fitnessnotes.fragments;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.n0153.fitnessnotes.ActivityExercises;
import com.n0153.fitnessnotes.NewSetActivity;
import com.n0153.fitnessnotes.R;
import com.n0153.fitnessnotes.db_utils.DBhelper;
import com.n0153.fitnessnotes.db_utils.ExOptionsData;


public class TrackTabFragment extends Fragment implements View.OnClickListener {

    private DBhelper dBhelper;
    TextView unitsTextView, amountTextView;
    EditText unitsAmountEditText, amountEditText, notesEditText;
    Button saveBtn, clearBtn;
    View divider11, divider12, divider13, divider14;
    LinearLayout setButtonsLayout, unitsAmountLayout;

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

        Log.d(LOG_TAG, "onCreateView");
        unitsTextView = v.findViewById(R.id.unitsTextView);
        amountTextView = v.findViewById(R.id.amountTextView);

        amountEditText = v.findViewById(R.id.amountEditText);
        unitsAmountEditText = v.findViewById(R.id.unitsAmountEditText);
        notesEditText = v.findViewById(R.id.notesEditText);

        saveBtn = v.findViewById(R.id.buttonSaveSet);
        clearBtn = v.findViewById(R.id.buttonClearSet);

        divider11 = v.findViewById(R.id.divider11);
        divider12 = v.findViewById(R.id.divider12);
        divider13 = v.findViewById(R.id.divider13);
        divider14 = v.findViewById(R.id.divider14);

        setButtonsLayout = v.findViewById(R.id.setButtonslatout);
        unitsAmountLayout = v.findViewById(R.id.unitsAmountLayout);


        saveBtn.setOnClickListener(this);
        clearBtn.setOnClickListener(this);

        setTextFields();
        setDividersSize();

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

    private void setDividersSize() {
        Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float dpWidth = outMetrics.widthPixels;

        ViewGroup.LayoutParams params = divider11.getLayoutParams();
        int widthhToset = (int) (dpWidth - 2 * (getResources().getDimension(R.dimen.divider_margin)));


        params.width = widthhToset;
        divider11.setLayoutParams(params);

        params = divider12.getLayoutParams();
        params.width = widthhToset;
        divider12.setLayoutParams(params);

        params = divider13.getLayoutParams();
        params.width = widthhToset;
        divider13.setLayoutParams(params);

        params = divider14.getLayoutParams();
        params.width = widthhToset;
        divider14.setLayoutParams(params);

        params = setButtonsLayout.getLayoutParams();
        params.width = widthhToset;
        setButtonsLayout.setLayoutParams(params);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case (R.id.buttonClearSet):
                unitsAmountEditText.setText("");
                amountEditText.setText("");
                notesEditText.setText("");
                break;

            case (R.id.buttonSaveSet):
                float weightOrDist = Float.parseFloat(unitsAmountEditText.getText().toString());
                float repsOrTime = Float.parseFloat(amountEditText.getText().toString());
                String notes = notesEditText.getText().toString();
                String name = ((NewSetActivity) getActivity()).getLabel();


                dBhelper.saveSet(name, weightOrDist, repsOrTime, notes);
                Log.d(LOG_TAG, " new set added to DB");

                Cursor c = dBhelper.getWritableDatabase().query(DBhelper.TABLE_SETS_NAME, null, null,
                null, null, null, null);

                Log.d(LOG_TAG, "Set table rows: " + c.getCount());

                break;
        }

    }

        private void rearangeElements(){
        divider11.setVisibility(View.INVISIBLE);
        divider12.setVisibility(View.INVISIBLE);
        unitsAmountLayout.setVisibility(View.INVISIBLE);

        }


}
