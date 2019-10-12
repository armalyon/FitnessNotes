package com.n0153.fitnessnotes;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.n0153.fitnessnotes.db_utils.DBhelper;
import com.n0153.fitnessnotes.dialogs.ModifyExerciseFragment;

import java.util.List;

public class ExercisesActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,
        View.OnClickListener, AdapterView.OnItemLongClickListener {

    public final static String EXERCISE_EXTRA = "Exercise";
    public final static String SELECTED_CATEGORY_EXTRA = "Category";
    public final static String SELECTED_EXERCISE_KEY = "Exercise to modify";
    public final static String MODIFY_EX_TAG = "Modify exercise dialog";


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
        listView.setOnItemLongClickListener(this);
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

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        String exercise = ((TextView)view.findViewById(R.id.textView2)).getText().toString();
        ModifyExerciseFragment fragment = new ModifyExerciseFragment();
        Bundle args = new Bundle();
        args.putString(SELECTED_EXERCISE_KEY, exercise);
        fragment.setArguments(args);
        fragment.show(getSupportFragmentManager(), MODIFY_EX_TAG );
        return true;
    }
}
