package com.n0153.fitnessnotes.fragments;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.n0153.fitnessnotes.NewSetActivity;
import com.n0153.fitnessnotes.R;
import com.n0153.fitnessnotes.db_utils.models.DBhelper;
import com.n0153.fitnessnotes.db_utils.models.ExOptionsData;


public class TrackTabFragment extends Fragment implements View.OnClickListener {

    private DBhelper dBhelper;
    TextView unitsTextView, amountTextView;
    EditText unitsAmountEditText, amountEditText, notesEditText, hoursEditText, minutesEditText, secondsEditText;
    Button saveBtn, clearBtn;
    View divider11, divider12, divider13, divider14, divider15, divider16;
    LinearLayout setButtonsLayout, unitsAmountLayout, amountLayout, timeFieldsLayout;
    String exercise;
    ConstraintLayout parentLayout;
    HistoryTabFragment historyTabFragment;


    public void setHistoryTabFragment(HistoryTabFragment historyTabFragment) {
        this.historyTabFragment = historyTabFragment;
    }

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

        exercise = ((NewSetActivity) getActivity()).getLabel();

        Log.d(LOG_TAG, "onCreateView, exercise: " + exercise);
        parentLayout = v.findViewById(R.id.saveSetConstraintLayout);
        unitsTextView = v.findViewById(R.id.unitsTextView);
        amountTextView = v.findViewById(R.id.amountTextView);

        amountLayout = v.findViewById(R.id.amountLayout);

        amountEditText = v.findViewById(R.id.amountEditText);
        unitsAmountEditText = v.findViewById(R.id.unitsAmountEditText);
        notesEditText = v.findViewById(R.id.notesEditText);
        hoursEditText = v.findViewById(R.id.editTextHH);
        minutesEditText = v.findViewById(R.id.editTextMM);
        secondsEditText = v.findViewById(R.id.editTextSS);

        saveBtn = v.findViewById(R.id.buttonSaveSet);
        clearBtn = v.findViewById(R.id.buttonClearSet);

        divider11 = v.findViewById(R.id.divider11);
        divider12 = v.findViewById(R.id.divider12);
        divider13 = v.findViewById(R.id.divider13);
        divider14 = v.findViewById(R.id.divider14);
        divider15 = v.findViewById(R.id.divider15);
        divider16 = v.findViewById(R.id.divider16);

        timeFieldsLayout = v.findViewById(R.id.layoutTimeFields);
        setButtonsLayout = v.findViewById(R.id.setButtonslatout);
        unitsAmountLayout = v.findViewById(R.id.unitsAmountLayout);


        saveBtn.setOnClickListener(this);
        clearBtn.setOnClickListener(this);

        setTextFields();
        setDividersSize();
        rearangeElements();
        rearangeTimeFields();

        return v;


    }


    private void setTextFields() {

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

        //method for setting of sizes for different screens
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

        params = divider15.getLayoutParams();
        params.width = widthhToset;
        divider15.setLayoutParams(params);

        params = divider16.getLayoutParams();
        params.width = widthhToset;
        divider16.setLayoutParams(params);

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
                hoursEditText.setText("");
                minutesEditText.setText("");
                secondsEditText.setText("");
                        break;
            case (R.id.buttonSaveSet):
                saveSet();
                historyTabFragment.updateMainList();
                break;
        }

    }

    private void rearangeElements() {

        //method to hide unnecessary views (dividers and text) if type requires only one value

        String type = dBhelper.getExeriseType(exercise);
        String timeType = getString(R.string.sp_time);
        String repsType = getString(R.string.sp_reps);

        if (type.equals(timeType) || type.equals(repsType)) {

            divider11.setVisibility(View.INVISIBLE);
            divider12.setVisibility(View.INVISIBLE);
            unitsAmountLayout.setVisibility(View.INVISIBLE);

            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) divider13.getLayoutParams();
            params.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
            params.topMargin = (int) getResources().getDimension(R.dimen.new_set_margin_top);

            if (type.equals(timeType)) amountTextView.setText(getString(R.string.item_time));
            if (type.equals(repsType)) amountTextView.setText(getString(R.string.item_reps));


        }

    }


//method to validete inputs and save set to DB

    private void saveSet() {

        String type = dBhelper.getExeriseType(exercise);

        String weightOrDistString = unitsAmountEditText.getText().toString();

        String notes = notesEditText.getText().toString();
        float weightOrDist = 0;
        String repsOrTime = "";

        if (type.equals(getString(R.string.sp_time))|| type.equals(getString(R.string.sp_dist_time))){
            String hh = hoursEditText.getText().toString();
            if (hh.equals("")|| hh.equals("0")) hh = "00";
            String mm = minutesEditText.getText().toString();
            if (mm.equals("") || mm.equals("0")) mm = "00";
            String ss = secondsEditText.getText().toString();
            if (ss.equals("") || ss.equals("0")) ss = "00";

                repsOrTime = hh + ":" + mm + ":" + ss;

            if (repsOrTime.equals("00:00:00")){
                Toast.makeText(getContext(), getString(R.string.toast_please_enter_valid_values),
                        Toast.LENGTH_SHORT).show();
                return;
                            }


        } else {repsOrTime = amountEditText.getText().toString();}

        //validations for weight/reps and dist/time
        if (type.equals(getString(R.string.sp_dist_time)) || type.equals(getString(R.string.sp_weight_reps))) {
            if (weightOrDistString.equals("") || weightOrDistString.equals("0") ||
                    repsOrTime.equals("") || repsOrTime.equals("0")) {

                Toast.makeText(getContext(), getString(R.string.toast_please_enter_valid_values),
                        Toast.LENGTH_SHORT).show();
            } else {
                weightOrDist = Float.parseFloat(weightOrDistString);
                dBhelper.saveSet(exercise, weightOrDist, repsOrTime, notes);
                showSnackbar();
                Log.d(LOG_TAG, " new set added to DB");
            }
        }

        // validations for reps/time
        if (type.equals(getString(R.string.sp_time)) || type.equals(getString(R.string.sp_reps))) {
            if (repsOrTime.equals("") || repsOrTime.equals("0")) {
                Toast.makeText(getContext(), getString(R.string.toast_please_enter_valid_values),
                        Toast.LENGTH_SHORT).show();
            } else {

                dBhelper.saveSet(exercise, weightOrDist, repsOrTime, notes);
                showSnackbar();
                Log.d(LOG_TAG, " new set added to DB");
            }


        }

        Cursor c = dBhelper.getWritableDatabase().query(DBhelper.TABLE_SETS_NAME, null, null,
                null, null, null, null);

        Log.d(LOG_TAG, "Set table rows: " + c.getCount());
        c.close();
    }

    //set visible time input fields and invisible units field
    private void rearangeTimeFields(){
        String type = dBhelper.getExeriseType(exercise);
        if (type.equals(getString(R.string.sp_time))|| type.equals(getString(R.string.sp_dist_time))){

            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(parentLayout);
            constraintSet.connect(R.id.layoutTimeFields , ConstraintSet.TOP, R.id.divider13, ConstraintSet.BOTTOM );

            constraintSet.applyTo(parentLayout);
            amountLayout.setVisibility(View.INVISIBLE);
            timeFieldsLayout.setVisibility(View.VISIBLE);


        }

    }

    private void showSnackbar (){
        Snackbar snackbar = Snackbar.make(parentLayout, getString(R.string.snackbar_set_saved), Snackbar.LENGTH_SHORT);
        View view = snackbar.getView();
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
        params.gravity = Gravity.TOP;
        view.setLayoutParams(params);
        view.setBackgroundColor(getResources().getColor(R.color.colorActionBar));
        snackbar.show();
    }

}
