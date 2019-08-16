package com.n0153.fitnessnotes;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.n0153.fitnessnotes.db_utils.DBhelper;
import com.n0153.fitnessnotes.dialogs.AddCategoryFragment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddExerciseActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private String dialogNewCatTag = "start dialog new category";

    ImageButton addCategoryBtn;
    Spinner categoriesSpinner, typeSpinner;
    DBhelper dBhelper;
    AddCategoryFragment dialogAddCategory;
    TextView amountTextView;
    EditText editName, editUnits;
    View divider7, divider8;

    String exerciseName, exerciseCategory, exerciseType, exrciseUnits;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);

        dBhelper = new DBhelper(this);

        addCategoryBtn = findViewById(R.id.addCategoryButton);
        addCategoryBtn.setOnClickListener(this);
        editName = findViewById(R.id.editName);
        editUnits = findViewById(R.id.editUnits);

        categoriesSpinner = findViewById(R.id.categoriesSpinner);
        typeSpinner = findViewById(R.id.typeSpinner);
        typeSpinner.setOnItemSelectedListener(this);

        dialogAddCategory = new AddCategoryFragment();

        amountTextView = findViewById(R.id.amountTextView);
        divider7 = findViewById(R.id.divider7);
        divider8 = findViewById(R.id.divider8);

        updateSpinner();

    }

    //save button listener methods

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save_ex, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        addExerciseToDB();

        return true;
    }

    //update activity methods

    @Override
    protected void onRestart() {
        super.onRestart();
        updateSpinner();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateSpinner();
    }


    // addCategory button listener method
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case (R.id.addCategoryButton):
                dialogAddCategory.show(getSupportFragmentManager(), dialogNewCatTag);
                break;
        }


    }


    public void updateSpinner() {

        Cursor cursor = dBhelper.getCategories();
        List<String> categoriesList = new ArrayList<>();
        categoriesList.add(getString(R.string.sp_please_select));
        if (cursor.moveToFirst()) {
            do {
                int index = cursor.getColumnIndex(DBhelper.KEY_CATEGORIES);
                categoriesList.add(cursor.getString(index));

            } while (cursor.moveToNext());
            cursor.close();
        }
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoriesList);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoriesSpinner.setAdapter(adapter);
    }


    private void addExerciseToDB() {

        //get values
        exerciseName = editName.getText().toString();
        exerciseCategory = categoriesSpinner.getSelectedItem().toString();
        exerciseType = typeSpinner.getSelectedItem().toString();
        exrciseUnits = editUnits.getText().toString();

        //check if all fields are filled
        if (exerciseName.equals("") || exerciseCategory.equals(getString(R.string.sp_please_select)) ||
                exerciseType.equals(getString(R.string.sp_please_select)) ||
                (exrciseUnits.equals("")) & (exerciseType.equals(getString(R.string.sp_weight_reps)) ||
                        exerciseType.equals(getString(R.string.sp_dist_time)))) {
            Toast.makeText(this, getString(R.string.toast_please_fill_fields), Toast.LENGTH_SHORT).show();
        } else {
            //Check if an exercise name exists
            boolean flag = false;
            List<String> exercisesList = dBhelper.getExNamesList();
            for (int i = 0; i < exercisesList.size(); i++) {
                if (exercisesList.get(i).equals(exerciseName)) flag = true;
            }
            if (flag) {
                Toast.makeText(this, getString(R.string.toast_ex_name_already_exist), Toast.LENGTH_SHORT).show();
                return;
            } else {
                //add to db
                ContentValues contentValues = new ContentValues();
                contentValues.put(DBhelper.KEY_CATEGORY, exerciseCategory);
                contentValues.put(DBhelper.KEY_NAME, exerciseName);
                contentValues.put(DBhelper.KEY_TYPE, exerciseType);
                contentValues.put(DBhelper.KEY_UNITS, exrciseUnits);
                dBhelper.getWritableDatabase().insert(DBhelper.TABLE_EXERISES_NAME, null, contentValues);
                finish();
            }


        }


    }


    //spiner's methods

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String itemValue = typeSpinner.getSelectedItem().toString();
        if (itemValue.equals(getString(R.string.sp_reps)) ||
                itemValue.equals(getString(R.string.sp_time))) {
            amountTextView.setText("");
            amountTextView.setVisibility(View.INVISIBLE);
            divider7.setVisibility(View.INVISIBLE);
            divider8.setVisibility(View.INVISIBLE);
            editUnits.setVisibility(View.INVISIBLE);
        }
        if (itemValue.equals(getString(R.string.sp_please_select)) ||
                itemValue.equals(getString(R.string.sp_dist_time)) ||
                itemValue.equals(getString(R.string.sp_weight_reps))) {
            amountTextView.setVisibility(View.VISIBLE);
            divider7.setVisibility(View.VISIBLE);
            divider8.setVisibility(View.VISIBLE);
            editUnits.setVisibility(View.VISIBLE);

        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}




