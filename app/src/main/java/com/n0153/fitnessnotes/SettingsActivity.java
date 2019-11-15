package com.n0153.fitnessnotes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.n0153.fitnessnotes.adapters.SettingsListAdapter;

public class SettingsActivity extends AppCompatActivity {

    private ListView settingsListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        settingsListView = findViewById(R.id.settingsListView);

        SettingsListAdapter adapter = new SettingsListAdapter(this);
        settingsListView.setAdapter(adapter);


    }
}
