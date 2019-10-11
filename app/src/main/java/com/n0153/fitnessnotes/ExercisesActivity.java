package com.n0153.fitnessnotes;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.n0153.fitnessnotes.db_utils.DBhelper;

import java.util.List;

public class ExercisesActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    public final static String EXERCISE_EXTRA = "Exercise";
    public final static String SELECTED_CATEGORY_EXTRA = "Category";


    String categoryToOpen;
    DBhelper dBhelper;
    ListView listView;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);
        Intent intent = getIntent();
        categoryToOpen = intent.getStringExtra(CategoriesActivity.CATEGORY_EXTRA);
        dBhelper = new DBhelper(this);
        listView = findViewById(R.id.exercisesListView);
        floatingActionButton = findViewById(R.id.addExerciseToCatButton);
        floatingActionButton.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(this, AddExerciseActivity.class);
        intent.putExtra(SELECTED_CATEGORY_EXTRA, categoryToOpen);
        startActivity(intent);


    }
}
