package com.n0153.fitnessnotes.dialogs;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.n0153.fitnessnotes.CategoriesActivity;
import com.n0153.fitnessnotes.R;


public class ModifyCategoryFragment extends DialogFragment implements View.OnClickListener {

    private String categoryName;
    private Button updateButton, deleteButton, cancelButton;
    private static final String UPDATE_TAG = "update category";

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

        switch (v.getId()) {
            case R.id.buttonCancelCategory:
                dismiss();
                break;
            case R.id.buttonDeleteCategory:


                dismiss();
                break;
            case R.id.buttonUpdateCategory:
                RenameCategoryFragment renameCategoryFragment = new RenameCategoryFragment();
                renameCategoryFragment.setCategoryToRename(categoryName);
                renameCategoryFragment.show(getFragmentManager(), UPDATE_TAG);

                dismiss();
                break;
        }

    }
}
