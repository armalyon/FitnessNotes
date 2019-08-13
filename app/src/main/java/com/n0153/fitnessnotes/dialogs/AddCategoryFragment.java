package com.n0153.fitnessnotes.dialogs;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.n0153.fitnessnotes.AddExerciseActivity;
import com.n0153.fitnessnotes.R;
import com.n0153.fitnessnotes.db_utils.DBhelper;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_LONG;


public class AddCategoryFragment extends DialogFragment implements View.OnClickListener {

    Button saveBtn, cancelBtn;
    EditText inputNewCategory;
    DBhelper dBhelper;
    private final String LOG_TAG = "Dialog log";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_category, container, false);
        saveBtn = v.findViewById(R.id.buttonSaveNewCategory);
        saveBtn.setOnClickListener(this);
        cancelBtn = v.findViewById(R.id.buttonCancel);
        cancelBtn.setOnClickListener(this);
        inputNewCategory = v.findViewById(R.id.editNewCategory);
        dBhelper = new DBhelper(getContext());

        return v;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonSaveNewCategory) {

            if (inputNewCategory.getText().toString().equals("")) {
                Log.d(LOG_TAG, "Empty category cannot be added");
                Toast.makeText(getContext(), getString(R.string.toast_please_enter_category), Toast.LENGTH_SHORT).show();
            } else {
                addCategory();

            }

        }
        if (v.getId() == R.id.buttonCancel) {
            inputNewCategory.setText("");
            dismiss();
        }


    }


    private void addCategory() {


        //get text from EditText
        String newCategoryName = inputNewCategory.getText().toString() + " ";

        //Get database
        SQLiteDatabase db = dBhelper.getWritableDatabase();

        //get categories values

        List<String> categoriesList = new ArrayList<>();

        //get put categories to the list/**/
        Cursor cursor = dBhelper.getCategories();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    int categoryIndex = cursor.getColumnIndex(DBhelper.KEY_CATEGORIES);
                    categoriesList.add(cursor.getString(categoryIndex));
                } while (cursor.moveToNext());
            }
            cursor.close();
        }


        //check if the category was not added before
        boolean flag = false;

        if (categoriesList.size() > 0) {

            for (int i = 0; i < categoriesList.size(); i++) {
                Log.d(LOG_TAG, categoriesList.get(i));
                if (categoriesList.get(i).equals(newCategoryName)) flag = true;
            }

        }

        Log.d(LOG_TAG, "List size is " + categoriesList.size());
        for (int i = 0; i < categoriesList.size(); i++) {
            Log.d(LOG_TAG, categoriesList.get(i));

        }


        //add category do DB
        if (!flag) {
            Log.d(LOG_TAG, "Trying to add new category");

            ContentValues contentValues = new ContentValues();
            contentValues.put(DBhelper.KEY_CATEGORIES, newCategoryName);

            db.insert(DBhelper.TABLE_CATEGORIES_NAME, null, contentValues);

            Toast.makeText(getContext(), getString(R.string.toast_new_category) + newCategoryName, Toast.LENGTH_SHORT).show();

            //clear editText and dismiss dialog
            inputNewCategory.setText("");

            dismiss();
            ((AddExerciseActivity)getActivity()).updateSpinner();

        } else
            Toast.makeText(getContext(), getString(R.string.toast_category_already_exist), LENGTH_LONG).show();


    }


}
