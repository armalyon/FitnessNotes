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
import android.widget.Toast;

import com.n0153.fitnessnotes.ExercisesActivity;
import com.n0153.fitnessnotes.R;


public class ModifyExerciseFragment extends DialogFragment implements View.OnClickListener {

    String exerciseName;
    Button updateButton, deleteButton, cancelButton;
    private final static String UPDATE_TAG = "Update name will be started";
    private final static String DELETE_TAG = "Delete ex will be started";
    public final static String EXERCISE_NAME_KEY = "Exercise to update";

    public ModifyExerciseFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        exerciseName = getArguments().getString(ExercisesActivity.SELECTED_EXERCISE_KEY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_modify_exercise, container, false);
        updateButton = v.findViewById(R.id.buttonUpdateExercise);
        deleteButton = v.findViewById(R.id.buttonDeleteExercise);
        cancelButton = v.findViewById(R.id.buttonCancelExercise);
        updateButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);


        return v;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonCancelExercise:
                dismiss();
                break;
            case R.id.buttonDeleteExercise:
                ConfirmExDeleteFragment confirmExDeleteFragment = new ConfirmExDeleteFragment();
                Bundle args1 = new Bundle();
                args1.putString(EXERCISE_NAME_KEY, exerciseName);
                confirmExDeleteFragment.setArguments(args1);
                confirmExDeleteFragment.show(getFragmentManager(), DELETE_TAG );
                dismiss();

                break;
            case R.id.buttonUpdateExercise:
                RenameExFragment renameExFragment = new RenameExFragment();
                Bundle args2 = new Bundle();
                args2.putString(EXERCISE_NAME_KEY, exerciseName);
                renameExFragment.setArguments(args2);
                renameExFragment.show(getFragmentManager(), UPDATE_TAG);
                dismiss();
            break;
        }
    }
}
