package com.n0153.fitnessnotes.dialogs;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.n0153.fitnessnotes.CategoriesActivity;
import com.n0153.fitnessnotes.R;
import com.n0153.fitnessnotes.db_utils.DBhelper;


public class RenameCategoryFragment extends DialogFragment implements View.OnClickListener {

    private Button renameButton, cancelButton;
    private EditText renameEditText;
    private String categoryToRename;

    public RenameCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_rename_category, container, false);
        renameButton = v.findViewById(R.id.buttonRenameCategory);
        cancelButton = v.findViewById(R.id.buttonCancelRenameCategory);
        renameButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
        renameEditText = v.findViewById(R.id.editRenameCategory);
        return v;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonRenameCategory:
                String newCategoryName = renameEditText.getText().toString();
                DBhelper dBhelper = new DBhelper(getContext());
                dBhelper.upddateCategoryName(categoryToRename, newCategoryName);
                dBhelper.close();
                ((CategoriesActivity)getActivity()).updateList();
                dismiss();
                break;
            case  R.id.buttonCancelRenameCategory:
                dismiss();
        }


    }

    public void setCategoryToRename(String categoryToRename) {
        this.categoryToRename = categoryToRename;
    }
}
