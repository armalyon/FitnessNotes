package com.n0153.fitnessnotes;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AddExerciseActivity extends AppCompatActivity {

    private ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);

        actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.title_add_ex);

    }
}
