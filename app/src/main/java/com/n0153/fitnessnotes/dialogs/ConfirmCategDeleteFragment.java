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

import com.n0153.fitnessnotes.CategoriesActivity;
import com.n0153.fitnessnotes.R;
import com.n0153.fitnessnotes.db_utils.DBhelper;


public class ConfirmCategDeleteFragment extends DialogFragment implements View.OnClickListener {

    private String categoryToDelete;
    private Button buttonDelete, buttonCancel;

    public ConfirmCategDeleteFragment() {
        // Required empty public constructor
    }

    public void setCategoryToDelete(String categoryToDelete) {
        this.categoryToDelete = categoryToDelete;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_confirm_categ_delete, container, false);
        buttonCancel = v.findViewById(R.id.buttonCancelCategDelete);
        buttonDelete = v.findViewById(R.id.buttonConfirmCategDelete);
        buttonDelete.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);

        return v;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonCancelCategDelete:

                dismiss();
                break;
            case R.id.buttonConfirmCategDelete:
                DBhelper dBhelper= new DBhelper(getContext());
                dBhelper.deleteCategoryAndExercises(categoryToDelete);

                dBhelper.close();
                ((CategoriesActivity)getActivity()).updateList();
                dismiss();
                break;
        }
    }
}
