package com.n0153.fitnessnotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NewSetActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_set);

        Intent intent = getIntent();
        String label = intent.getStringExtra(ActivityExercises.EXERCISE_EXTRA);
        setTitle(label);


    }
}
