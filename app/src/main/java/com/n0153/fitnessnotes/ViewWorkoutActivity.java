package com.n0153.fitnessnotes;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.n0153.fitnessnotes.dialogs.ModifySetFragment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ViewWorkoutActivity extends AppCompatActivity {

    private SimpleDateFormat labelDateFormat;
    final String datePatternLabel = "yyyy, dd MMMM,  E";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_workout);
        long dateLong =  getIntent().getLongExtra(ModifySetFragment.EXERCISE_DATE_LONG_KEY2, 0);
        Date date = new Date(dateLong);
        labelDateFormat = new SimpleDateFormat(datePatternLabel);
        setTitle(labelDateFormat.format(date));

    }
}
