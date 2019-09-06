package com.n0153.fitnessnotes.dialogs;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.n0153.fitnessnotes.R;

public class ViewWorkoutFragment extends DialogFragment implements View.OnClickListener {


    public View onCreateView( LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_view_workout, container, false);


        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonViewWorkout :

                //add code

                break;
            case  R.id.buttonCancelHistory :
              dismiss();
            break;
        }


    }
}
