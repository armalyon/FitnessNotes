package com.n0153.fitnessnotes;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.n0153.fitnessnotes.fragments.WorkoutFragment;
import com.n0153.fitnessnotes.interfaces.DateGettable;
import com.n0153.fitnessnotes.interfaces.WorkoutFragmentGettable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DateGettable, WorkoutFragmentGettable {

    private FloatingActionButton floatingActionButton;
    private ImageButton leftSlideButton, rightSlideButton;
    private final String LOG_TAG_MAIN = "Workouts Log:";
    private FrameLayout fragmentContainer;

    private TextView header;
    private WorkoutFragment workoutFragment;


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
        rightSlideButton.setOnClickListener(this);
        leftSlideButton.setOnClickListener(this);

        header = findViewById(R.id.mainDateTextView);

        dateFormat = new SimpleDateFormat(headerDatePattern);
        Date date = new Date();
        dateLong = date.getTime();
        dateString = dateFormat.format(date);
        header.setText(dateString);

        workoutFragment = (WorkoutFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentViewWorkout);

        fragmentContainer = findViewById(R.id.mainFragmentContainer);

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
            case (R.id.rightSlideButton):
//for tests, should be changed
                Log.d(LOG_TAG_MAIN, "OnClick");
                dateLong+=86400000;

                openRightFragment();
                break;

            case (R.id.leftSlideButton):
                dateLong-=86400000;
                openLeftFragment();

                break;
        }


    }

    @Override
    public long getLongDate() {
        return dateLong;
    }

    public void openRightFragment() {
        workoutFragment = new WorkoutFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_to_right, R.anim.exit_from_right, R.anim.slide_to_right, R.anim.exit_from_right);

        transaction.add(R.id.mainFragmentContainer, workoutFragment, "WorkoutFragment" );
        transaction.commit();

    }


    public void openLeftFragment() {
        workoutFragment = new WorkoutFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.exit_from_right, R.anim.slide_to_right, R.anim.exit_from_right, R.anim.slide_to_right);

       transaction.add(R.id.mainFragmentContainer, workoutFragment, "WorkoutFragment" );
        transaction.commit();

    }


    @Override
    public WorkoutFragment getWorkoutFragment() {
        return workoutFragment;
    }
}
