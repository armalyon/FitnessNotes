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


public class RenameExFragment extends DialogFragment implements View.OnClickListener {

    private String exerciseToReneame;
    private Button renameButton, cancelButton;


    public RenameExFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_rename_ex, container, false);
        renameButton = v.findViewById(R.id.buttonRenameEx);
        cancelButton = v.findViewById(R.id.buttonCancelRenameEx);
        renameButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);

        return v;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        exerciseToReneame = getArguments().getString(ModifyExerciseFragment.EXERCISE_NAME_KEY);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonCancelRenameEx:
                dismiss();
                break;
            case R.id.buttonRenameEx:


                dismiss();
                break;

        }
    }
}
