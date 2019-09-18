package com.n0153.fitnessnotes;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.n0153.fitnessnotes.dialogs.ModifySetFragment;
import com.n0153.fitnessnotes.fragments.WorkoutFragment;




public class ViewWorkoutActivity extends AppCompatActivity {
    long dateLong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_workout);
        dateLong =  getIntent().getLongExtra(ModifySetFragment.EXERCISE_DATE_LONG_KEY2, 0);
        setTitle(getString(R.string.title_workout));

    }

    public long getDateLong() {
        return dateLong;
    }
}
