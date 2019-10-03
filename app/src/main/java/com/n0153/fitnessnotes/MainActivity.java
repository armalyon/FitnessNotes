package com.n0153.fitnessnotes;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.n0153.fitnessnotes.db_utils.DBhelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton floatingActionButton;
    private ImageButton leftSlideButton, rightSlideButton;
    private final String LOG_TAG_MAIN = "Workouts Log:";

    final String headerDatePattern = "yyyy, dd MMMM,  E";
    private SimpleDateFormat dateFormat;
    String dateString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        floatingActionButton = findViewById(R.id.addTrainingButton);
        floatingActionButton.setOnClickListener(this);
        rightSlideButton = findViewById(R.id.rightSlideButton);
        leftSlideButton = findViewById(R.id.leftSlideButton);

        dateFormat = new SimpleDateFormat(headerDatePattern);
        Date date = new Date();
        dateString = dateFormat.format(date);

        Log.d(LOG_TAG_MAIN, "OnCreate done");
    }

    @Override
    public void onClick(View v) {

        Intent intent;
        switch (v.getId()) {
            case (R.id.addTrainingButton):
                Log.d(LOG_TAG_MAIN, "Add pressed");
                intent = new Intent(this, CategoriesActivity.class);
                startActivity(intent);
                break;
        }


    }
}
