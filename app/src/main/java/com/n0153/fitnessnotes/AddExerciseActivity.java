package com.n0153.fitnessnotes;

import android.database.Cursor;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.n0153.fitnessnotes.db_utils.DBhelper;
import com.n0153.fitnessnotes.dialogs.AddCategoryFragment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddExerciseActivity extends AppCompatActivity implements View.OnClickListener {

    private String dialogNewCatTag = "start dialog new category";

    ImageButton addCategoryBtn;
    Spinner categoriesSpinner;
    DBhelper dBhelper;
    AddCategoryFragment dialogAddCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);

        dBhelper = new DBhelper(this);

        addCategoryBtn = findViewById(R.id.addCategoryButton);
        addCategoryBtn.setOnClickListener(this);

        categoriesSpinner = findViewById(R.id.categoriesSpinner);

        dialogAddCategory = new AddCategoryFragment();

        updateSpinner();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save_ex, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        return true;
    }

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




    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case (R.id.addCategoryButton):
                dialogAddCategory.show(getSupportFragmentManager(), dialogNewCatTag);

                break;
        }

    }



    private void updateSpinner(){

        Cursor cursor = dBhelper.getCategories();
        List <String> categoriesList = new ArrayList<>();
        categoriesList.add(getString(R.string.sp_please_select));
        if (cursor.moveToFirst()){
            do {
                int index = cursor.getColumnIndex(DBhelper.KEY_CATEGORIES);
                categoriesList.add(cursor.getString(index));

            }while (cursor.moveToNext());
            cursor.close();
        }
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoriesList);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoriesSpinner.setAdapter(adapter);
    }
}
