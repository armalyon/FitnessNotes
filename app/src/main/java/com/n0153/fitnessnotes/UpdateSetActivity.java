package com.n0153.fitnessnotes;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.n0153.fitnessnotes.db_utils.DBhelper;
import com.n0153.fitnessnotes.db_utils.models.ExOptionsDataModel;
import com.n0153.fitnessnotes.dialogs.ModifySetFragment;
import com.n0153.fitnessnotes.fragments.HistoryTabFragment;

public class UpdateSetActivity extends AppCompatActivity implements View.OnClickListener {

    private DBhelper dBhelper;
    TextView unitsTextView, amountTextView;
    EditText unitsAmountEditText, amountEditText, notesEditText, hoursEditText, minutesEditText, secondsEditText;
    Button updateBtn, clearBtn;
    View divider11, divider12, divider13, divider14, divider15, divider16;
    LinearLayout setButtonsLayout, unitsAmountLayout, amountLayout, timeFieldsLayout;
    String exercise;
    ConstraintLayout parentLayout;
    private final String LOG_TAG = "Update set Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_set);
        Intent intent = getIntent();

        exercise = intent.getStringExtra(ModifySetFragment.EXERCISE_KEY);

        dBhelper = new DBhelper(this);

        parentLayout = findViewById(R.id.saveSetConstraintLayout);
        unitsTextView = findViewById(R.id.unitsTextView);
        amountTextView = findViewById(R.id.amountTextView);

        amountLayout = findViewById(R.id.amountLayout);

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
        String type = exOptionsData.getType();

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



    @Override
    public void onClick(View v) {

    }



}
