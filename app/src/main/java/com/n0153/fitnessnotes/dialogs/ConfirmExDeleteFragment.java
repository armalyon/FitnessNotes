package com.n0153.fitnessnotes.dialogs;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.n0153.fitnessnotes.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConfirmExDeleteFragment extends DialogFragment implements View.OnClickListener {

    private String exerciseToDelete;
    private Button buttonDelete, buttonCancel;


    public ConfirmExDeleteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_confirm_ex_delete, container, false);
        buttonCancel = v.findViewById(R.id.buttonCancelExDelete);
        buttonDelete = v.findViewById(R.id.buttonConfirmExDelete);
        buttonDelete.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);

        return v;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        exerciseToDelete = getArguments().getString(ModifyExerciseFragment.EXERCISE_NAME_KEY);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonCancelExDelete:


                dismiss();
                break;
            case R.id.buttonConfirmExDelete:


                dismiss();
                break;

        }

    }
}
