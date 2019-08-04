package com.n0153.fitnessnotes.dialogs;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.n0153.fitnessnotes.R;

import static android.widget.Toast.LENGTH_LONG;


public class AddCategoryFragment extends DialogFragment implements View.OnClickListener {

    Button saveBtn;
    EditText inputNewCategory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_category, container, false);
        saveBtn = v.findViewById(R.id.buttonSaveNewCategory);
        saveBtn.setOnClickListener(this);
        inputNewCategory = v.findViewById(R.id.editNewCategory);

        return v;
    }


    @Override
    public void onClick(View v) {
        if (v.getId()== R.id.buttonSaveNewCategory){


            Toast.makeText(getContext(), R.string.toast_save_category, LENGTH_LONG).show();
            dismiss();
        }


    }
}
