package com.n0153.fitnessnotes;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class CategoriesActivity extends AppCompatActivity implements View.OnClickListener {


    private FloatingActionButton floatingActionButton;
    private final String LOG_TAG_WORKOUTS = "Catgories Log:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        floatingActionButton = findViewById(R.id.add_exercise);
        floatingActionButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        Intent intent;
        switch (v.getId()) {
            case (R.id.add_exercise):
                Log.d(LOG_TAG_WORKOUTS, "Add pressed");
                intent = new Intent(this, AddExerciseActivity.class);
                startActivity(intent);
                break;

        }
    }
}