package com.n0153.fitnessnotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.n0153.fitnessnotes.db_utils.models.DBhelper;

import java.util.List;

public class ActivityExercises extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public final static String EXERCISE_EXTRA = "Exercise";

    String categoryToOpen;
    DBhelper dBhelper;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);
        Intent intent = getIntent();
        categoryToOpen = intent.getStringExtra(CategoriesActivity.CATEGORY_EXTRA);
        dBhelper = new DBhelper(this);
        listView = findViewById(R.id.exercisesListView);
        updateList();
        listView.setOnItemClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        updateList();

    }

    private void updateList(){
        List<String> exerciesesList = dBhelper.getExercisesList(categoryToOpen);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.listraw_layout, R.id.textView2, exerciesesList);
        listView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String selectedExercise  =  (String)parent.getItemAtPosition(position);
        Intent intent = new Intent(this, NewSetActivity.class);
        intent.putExtra(EXERCISE_EXTRA, selectedExercise );
        startActivity(intent);


    }
}
