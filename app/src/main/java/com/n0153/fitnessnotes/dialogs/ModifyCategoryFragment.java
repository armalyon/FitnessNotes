package com.n0153.fitnessnotes.dialogs;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.n0153.fitnessnotes.CategoriesActivity;
import com.n0153.fitnessnotes.R;


public class ModifyCategoryFragment extends DialogFragment implements View.OnClickListener {


    String categoryName;
    Button updateButton, deleteButton, cancelButton;

    public ModifyCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoryName = getArguments().getString(CategoriesActivity.SELECTED_CATEGORY_KEY);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_modify_category, container, false);
        updateButton = v.findViewById(R.id.buttonUpdateCategory);
        deleteButton = v.findViewById(R.id.buttonDeleteCategory);
        cancelButton = v.findViewById(R.id.buttonCancelCategory);
        updateButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);


        return v;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.buttonCancelCategory:
                dismiss();
                break;
            case R.id.buttonDeleteCategory:

                break;
            case R.id.buttonUpdateCategory:


            break;
        }

    }
}
