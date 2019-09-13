package com.n0153.fitnessnotes;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.n0153.fitnessnotes.db_utils.DBhelper;
import com.n0153.fitnessnotes.db_utils.models.ExOptionsDataModel;
import com.n0153.fitnessnotes.db_utils.models.SetOptionsDataModel;
import com.n0153.fitnessnotes.dialogs.ModifySetFragment;
import com.n0153.fitnessnotes.fragments.HistoryTabFragment;

public class UpdateSetActivity extends AppCompatActivity implements View.OnClickListener {

    private DBhelper dBhelper;
    TextView unitsTextView, amountTextView;
    EditText unitsAmountEditText, amountEditText, notesEditText, hoursEditText, minutesEditText, secondsEditText;
    Button updateBtn, clearBtn;
    View divider11, divider12, divider13, divider14, divider15, divider16;
    LinearLayout setButtonsLayout, unitsAmountLayout, amountLayout, timeFieldsLayout;
    String exercise, type;
    ConstraintLayout parentLayout;
    long dateLong;
    private final String LOG_TAG = "Update set Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_set);
        Intent intent = getIntent();

        exercise = intent.getStringExtra(ModifySetFragment.EXERCISE_KEY);
        dateLong = intent.getLongExtra(ModifySetFragment.EXERCISE_DATE_LONG_KEY1, 0);

        dBhelper = new DBhelper(this);

        type = dBhelper.getExeriseType(exercise);

        parentLayout = findViewById(R.id.saveSetConstraintLayout);

        unitsTextView = findViewById(R.id.unitsTextView);
        amountTextView = findViewById(R.id.amountTextView);

        amountLayout = findViewById(R.id.amountLayout);

        parentLayout = findViewById(R.id.saveSetConstraintLayout);

        amountEditText = findViewById(R.id.amountEditText);
        unitsAmountEditText = findViewById(R.id.unitsAmountEditText);
        notesEditText = findViewById(R.id.notesEditText);
        hoursEditText = findViewById(R.id.editTextHH);
        minutesEditText = findViewById(R.id.editTextMM);
        secondsEditText = findViewById(R.id.editTextSS);

        updateBtn = findViewById(R.id.buttonSaveSet);
        clearBtn = findViewById(R.id.buttonClearSet);

        divider11 = findViewById(R.id.divider11);
        divider12 = findViewById(R.id.divider12);
        divider13 = findViewById(R.id.divider13);
        divider14 = findViewById(R.id.divider14);
        divider15 = findViewById(R.id.divider15);
        divider16 = findViewById(R.id.divider16);

        timeFieldsLayout = findViewById(R.id.layoutTimeFields);
        setButtonsLayout = findViewById(R.id.setButtonslatout);
        unitsAmountLayout = findViewById(R.id.unitsAmountLayout);

        updateBtn.setOnClickListener(this);

        updateBtn.getBackground().setColorFilter(getResources().getColor(R.color.colorUpdateButton), PorterDuff.Mode.SRC);

        updateBtn.setText(R.string.item_update);

        clearBtn.setOnClickListener(this);

        setTextFields();
        setDividersSize();

        rearangeElements();
        rearangeTimeFields();

        setFieldsValues();
    }


    private void setDividersSize() {

        //method for setting of sizes for different screens
        Display display = getWindowManager().getDefaultDisplay();
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

    private void setTextFields() {

        Log.d(LOG_TAG, exercise);
        ExOptionsDataModel exOptionsData = dBhelper.getExOptionsData(exercise);
        unitsTextView.setText(exOptionsData.getUnits() + ": ");

        String amountOf = null;

        if (type.equals(getString(R.string.sp_weight_reps)))
            amountOf = getString(R.string.item_reps);
        else amountOf = getString(R.string.item_time);

        amountTextView.setText(amountOf);
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

    //set visible time input fields and invisible units field
    private void rearangeTimeFields() {
        String type = dBhelper.getExeriseType(exercise);
        if (type.equals(getString(R.string.sp_time)) || type.equals(getString(R.string.sp_dist_time))) {

            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(parentLayout);
            constraintSet.connect(R.id.layoutTimeFields, ConstraintSet.TOP, R.id.divider13, ConstraintSet.BOTTOM);

            constraintSet.applyTo(parentLayout);
            amountLayout.setVisibility(View.INVISIBLE);
            timeFieldsLayout.setVisibility(View.VISIBLE);


        }

    }


    //get fields values from previous set
    private void setFieldsValues() {
        SetOptionsDataModel set = dBhelper.getSetByDate(dateLong);

        String type = dBhelper.getExeriseType(exercise);

        unitsAmountEditText.setText(set.getWeightOrDist());
        if ((type.equals(getString(R.string.sp_weight_reps)) || type.equals(getString(R.string.sp_reps)))) {
            amountEditText.setText(set.getRepsOrTime());
        }

        if ((type.equals(getString(R.string.sp_time))) || type.equals(getString(R.string.sp_dist_time))) {
            String quantity = set.getRepsOrTime();
            hoursEditText.setText(quantity.substring(0, 2));
            minutesEditText.setText(quantity.substring(3, 5));
            secondsEditText.setText(quantity.substring(6));
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSaveSet:
                updateSet();
                break;

            case R.id.buttonClearSet:
                unitsAmountEditText.setText("");
                amountEditText.setText("");
                notesEditText.setText("");
                hoursEditText.setText("");
                minutesEditText.setText("");
                secondsEditText.setText("");
                break;

        }

    }


    private void updateSet() {

        String weightOrDistString = unitsAmountEditText.getText().toString();

        String notes = notesEditText.getText().toString();

        String repsOrTime = "";

        if (type.equals(getString(R.string.sp_time)) || type.equals(getString(R.string.sp_dist_time))) {
            String hh = hoursEditText.getText().toString();
            if (hh.equals("") || hh.equals("0")) hh = "00";
            if (hh.length() < 2) hh = "0" + hh;
            String mm = minutesEditText.getText().toString();
            if (mm.equals("") || mm.equals("0")) mm = "00";
            if (mm.length() < 2) mm = "0" + mm;
            String ss = secondsEditText.getText().toString();
            if (ss.equals("") || ss.equals("0")) ss = "00";
            if (ss.length() < 2) ss = "0" + ss;

            repsOrTime = hh + ":" + mm + ":" + ss;

            if (repsOrTime.equals("00:00:00")) {
                Toast.makeText(this, getString(R.string.toast_please_enter_valid_values),
                        Toast.LENGTH_SHORT).show();
                return;
            }


        } else {
            repsOrTime = amountEditText.getText().toString();
        }

        //validations for weight/reps and dist/time
        if (type.equals(getString(R.string.sp_dist_time)) || type.equals(getString(R.string.sp_weight_reps))) {
            if (weightOrDistString.equals("") || weightOrDistString.equals("0") ||
                    repsOrTime.equals("") || repsOrTime.equals("0")) {

                Toast.makeText(this, getString(R.string.toast_please_enter_valid_values),
                        Toast.LENGTH_SHORT).show();
            } else {
                dBhelper.updateSet(dateLong, weightOrDistString, repsOrTime, notes);
                showSnackbar();
                HistoryTabFragment.getInstance().updateMainList();
                finish();
                Log.d(LOG_TAG, " new set updated");
            }
        }

        // validations for reps/time
        if (type.equals(getString(R.string.sp_time)) || type.equals(getString(R.string.sp_reps))) {
            if (repsOrTime.equals("") || repsOrTime.equals("0")) {
                Toast.makeText(this, getString(R.string.toast_please_enter_valid_values),
                        Toast.LENGTH_SHORT).show();
            } else {

                dBhelper.updateSet(dateLong, weightOrDistString, repsOrTime, notes);
                showSnackbar();
                HistoryTabFragment.getInstance().updateMainList();
                finish();
                Log.d(LOG_TAG, "  set updated to DB");
            }

        }


    }

    private void showSnackbar() {
        Snackbar snackbar = Snackbar.make(parentLayout, getString(R.string.snackbar_set_updated), Snackbar.LENGTH_SHORT);
        View view = snackbar.getView();
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
        params.gravity = Gravity.TOP;
        view.setLayoutParams(params);
        view.setBackgroundColor(getResources().getColor(R.color.colorUpdateButton));
        snackbar.show();
    }


}
