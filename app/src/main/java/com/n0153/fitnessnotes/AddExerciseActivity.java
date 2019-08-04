package com.n0153.fitnessnotes;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

public class AddExerciseActivity extends AppCompatActivity {

    private ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);

        actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(R.string.title_add_ex);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save_ex, menu);
        return true;
    }
}
