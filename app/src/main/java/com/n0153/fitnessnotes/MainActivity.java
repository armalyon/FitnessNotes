package com.n0153.fitnessnotes;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.n0153.fitnessnotes.db_utils.DBhelper;
import com.n0153.fitnessnotes.db_utils.models.SetOptionsDataModel;
import com.n0153.fitnessnotes.fragments.WorkoutFragment;
import com.n0153.fitnessnotes.interfaces.DateGettable;
import com.n0153.fitnessnotes.interfaces.WorkoutFragmentGettable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DateGettable, WorkoutFragmentGettable {

    private FloatingActionButton floatingActionButton;
    private ImageButton leftSlideButton, rightSlideButton;
    private final String LOG_TAG_MAIN = "Workouts Log:";

    private TextView header;
    private WorkoutFragment workoutFragment;
    public static final int MILS_IN_A_DAY = 86400000;


    private SimpleDateFormat dateFormat;
    String dateString;
    private long dateLong;
    private DBhelper dBhelper;

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
        dBhelper = new DBhelper(this);

        openTodayFragment();

    }

    @Override
    public void onClick(View v) {

        Intent intent;
        switch (v.getId()) {
            case (R.id.addTrainingButton):
                intent = new Intent(this, CategoriesActivity.class);
                startActivity(intent);
                break;
            case (R.id.rightSlideButton):
                if (dBhelper.isNextDatesAvailable(dateLong)) {
                    setNextAvailableDate(1);
                    header.setText(dateFormat.format(new Date(dateLong)));
                    openRightFragment();
                }
                break;

            case (R.id.leftSlideButton):
                if (dBhelper.isPrevDatesAvailable(dateLong)) {
                    setNextAvailableDate(-1);
                    header.setText(dateFormat.format(new Date(dateLong)));
                    openLeftFragment();
                }
                break;
        }


    }

    @Override
    public long getLongDate() {
        return dateLong;
    }



    private void setNextAvailableDate(int sign) {
        int day = sign * MILS_IN_A_DAY;

        long nextDay = dateLong+ day;

        ArrayList<SetOptionsDataModel> list = dBhelper.getSetsByDay(nextDay);;

       while (list.size()<=0){
           nextDay+=day;
           list = dBhelper.getSetsByDay(nextDay);

       }

        dateLong = nextDay;

    }


    private void openRightFragment() {

        workoutFragment = new WorkoutFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_left, R.animator.enter_from_left, R.animator.exit_to_right);
        transaction.replace(R.id.mainFragmentContainer, workoutFragment);
        transaction.commit();

    }


    private void openLeftFragment() {

        workoutFragment = new WorkoutFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.animator.enter_from_left, R.animator.exit_to_right, R.animator.enter_from_right, R.animator.enter_from_left);
        transaction.replace(R.id.mainFragmentContainer, workoutFragment);
        transaction.commit();

    }

    private void openTodayFragment() {
        workoutFragment = new WorkoutFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.mainFragmentContainer, workoutFragment);
        transaction.commit();

    }




    @Override
    public WorkoutFragment getWorkoutFragment() {
        return workoutFragment;
    }


}


