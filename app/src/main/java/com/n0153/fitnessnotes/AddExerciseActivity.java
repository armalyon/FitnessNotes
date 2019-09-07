package com.n0153.fitnessnotes;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.n0153.fitnessnotes.db_utils.models.DBhelper;
import com.n0153.fitnessnotes.dialogs.AddCategoryFragment;

import java.util.ArrayList;
import java.util.List;

public class AddExerciseActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private String dialogNewCatTag = "start dialog new category";

    ImageButton addCategoryBtn;
    Spinner categoriesSpinner, typeSpinner;
    DBhelper dBhelper;
    AddCategoryFragment dialogAddCategory;
    TextView amountTextView;
    EditText editName, editUnits;
    View divider1, divider2, divider3, divider4, divider5, divider6, divider7, divider8;

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
        divider1 = findViewById(R.id.divider1);
        divider2 = findViewById(R.id.divider2);
        divider3 = findViewById(R.id.divider3);
        divider4 = findViewById(R.id.divider4);
        divider5 = findViewById(R.id.divider5);
        divider6 = findViewById(R.id.divider6);

        updateSpinner();
        setDividersSize();
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

    //set dividers width corresponding to screen size
    private void setDividersSize() {

        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float dpWidth = outMetrics.widthPixels;


        int widthhToset = (int) (dpWidth - 2 * (getResources().getDimension(R.dimen.divider_margin)));
        ViewGroup.LayoutParams params = divider1.getLayoutParams();

        params.width = widthhToset;
        divider1.setLayoutParams(params);

        params = divider2.getLayoutParams();
        params.width = widthhToset;
        divider2.setLayoutParams(params);


        params = divider3.getLayoutParams();
        params.width = widthhToset;
        divider3.setLayoutParams(params);

        params = divider4.getLayoutParams();
        params.width = widthhToset;
        divider4.setLayoutParams(params);

        params = divider5.getLayoutParams();
        params.width = widthhToset;
        divider5.setLayoutParams(params);

        params = divider6.getLayoutParams();
        params.width = widthhToset;
        divider6.setLayoutParams(params);

        params = divider7.getLayoutParams();
        params.width = widthhToset;
        divider7.setLayoutParams(params);

        params = divider8.getLayoutParams();
        params.width = widthhToset;
        divider8.setLayoutParams(params);

    }

}




