package com.n0153.fitnessnotes;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.n0153.fitnessnotes.adapters.SectionsPageAdapter;
import com.n0153.fitnessnotes.fragments.HistoryTabFragment;
import com.n0153.fitnessnotes.fragments.TrackTabFragment;

public class NewSetActivity extends AppCompatActivity {


    private final String LOG_TAG = "New Set Activity";

    private ViewPager viewPager;
    String label;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_set);

        Log.d(LOG_TAG, "onCreate started");

        Intent intent = getIntent();
        label = intent.getStringExtra(ActivityExercises.EXERCISE_EXTRA);
        setTitle(label);

        viewPager = findViewById(R.id.container);
        setupViewPager(viewPager);

        TabLayout tabLayout = findViewById(R.id.setTabs);
        tabLayout.setupWithViewPager(viewPager);


    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        TrackTabFragment trackTabFragment = new TrackTabFragment();
        HistoryTabFragment historyTabFragment = new HistoryTabFragment();

        //this needed for access to updateMainlist()
        trackTabFragment.setHistoryTabFragment(historyTabFragment);

        adapter.addFragment(trackTabFragment, getString(R.string.tab_track));
        adapter.addFragment(historyTabFragment, getString(R.string.tab_history));
        viewPager.setAdapter(adapter);
    }

    public String getLabel() {
        return label;
    }


}
