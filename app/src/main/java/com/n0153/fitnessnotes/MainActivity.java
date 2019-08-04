package com.n0153.fitnessnotes;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.n0153.fitnessnotes.db_utils.DBhelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton floatingActionButton;
    private final String LOG_TAG_MAIN = "Workouts Log:";

    DBhelper dBhelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton = findViewById(R.id.addTrainingButton);
        floatingActionButton.setOnClickListener(this);

        dBhelper = new DBhelper(this);
        db = dBhelper.getWritableDatabase();

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
