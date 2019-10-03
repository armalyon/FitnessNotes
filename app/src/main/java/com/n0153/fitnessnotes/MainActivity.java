package com.n0153.fitnessnotes;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.n0153.fitnessnotes.db_utils.DBhelper;
import com.n0153.fitnessnotes.interfaces.DateGettable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DateGettable {

    private FloatingActionButton floatingActionButton;
    private ImageButton leftSlideButton, rightSlideButton;
    private final String LOG_TAG_MAIN = "Workouts Log:";

    private TextView header;


    private SimpleDateFormat dateFormat;
    String dateString;
    private long dateLong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        floatingActionButton = findViewById(R.id.addTrainingButton);
        floatingActionButton.setOnClickListener(this);
        rightSlideButton = findViewById(R.id.rightSlideButton);
        leftSlideButton = findViewById(R.id.leftSlideButton);
        header = findViewById(R.id.mainDateTextView);
        dateFormat = new SimpleDateFormat(headerDatePattern);
        Date date = new Date();
        dateLong = date.getTime();
        dateString = dateFormat.format(date);
        header.setText(dateString);

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

    @Override
    public long getLongDate() {
        return dateLong;
    }
}
