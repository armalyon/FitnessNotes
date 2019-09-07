package com.n0153.fitnessnotes;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.n0153.fitnessnotes.db_utils.models.DBhelper;

import java.util.ArrayList;
import java.util.List;

public class CategoriesActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {


    private FloatingActionButton floatingActionButton;
    private final String LOG_TAG = "Categories_Log:";
    private DBhelper dBhelper;
    private ListView listView;
    final static String CATEGORY_EXTRA = "CATEGORY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        floatingActionButton = findViewById(R.id.add_exercise);
        floatingActionButton.setOnClickListener(this);
        dBhelper = new DBhelper(this);
        listView = findViewById(R.id.lvCategories);
        listView.setOnItemClickListener(this);

        updateList();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume invocation");
        updateList();
    }

    @Override
    public void onClick(View v) {

        Intent intent;
        switch (v.getId()) {
            case (R.id.add_exercise):
                Log.d(LOG_TAG, "Add pressed");
                intent = new Intent(this, AddExerciseActivity.class);
                startActivity(intent);
                break;

        }
    }


    private void updateList() {
        Cursor cursor = dBhelper.getCategories();
        List<String> categoriesList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                int index = cursor.getColumnIndex(DBhelper.KEY_CATEGORIES);
                categoriesList.add(cursor.getString(index));

            } while (cursor.moveToNext());
            cursor.close();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.listraw_layout, R.id.textView2, categoriesList);

        listView.setAdapter(adapter);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String selectedCategory  =  (String)parent.getItemAtPosition(position);
        Intent intent = new Intent(this, ActivityExercises.class);
        intent.putExtra(CATEGORY_EXTRA, selectedCategory);
        startActivity(intent);

    }
}