package com.n0153.fitnessnotes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.n0153.fitnessnotes.adapters.SettingsListAdapter;
import com.n0153.fitnessnotes.dialogs.BackupFragment;

public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView settingsListView;
    private final String BACKUP_TAG = "backup start";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        settingsListView = findViewById(R.id.settingsListView);

        SettingsListAdapter adapter = new SettingsListAdapter(this);
        settingsListView.setAdapter(adapter);
        settingsListView.setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        switch (position) {
            case 0:
                BackupFragment backupFragment = new BackupFragment();
                backupFragment.show(getSupportFragmentManager(), BACKUP_TAG);
                break;
        }

    }
}
