package com.n0153.fitnessnotes.dialogs;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.n0153.fitnessnotes.NewSetActivity;
import com.n0153.fitnessnotes.R;
import com.n0153.fitnessnotes.Types.AdapterType;
import com.n0153.fitnessnotes.UpdateSetActivity;
import com.n0153.fitnessnotes.ViewWorkoutActivity;
import com.n0153.fitnessnotes.adapters.CardRecyclerAdapter;
import com.n0153.fitnessnotes.db_utils.DBhelper;
import com.n0153.fitnessnotes.fragments.HistoryTabFragment;
import com.n0153.fitnessnotes.fragments.WorkoutFragment;


public class ModifySetFragment extends DialogFragment implements View.OnClickListener {

    private Button updateButton, deleteButton, cancelButton, viewWorkoutButton;
    private DBhelper dBhelper;


    private String exercise;
    public static final String EXERCISE_KEY = "exercise";
    public static final String EXERCISE_DATE_LONG_KEY1 = "date long 1";
    public static final String EXERCISE_DATE_LONG_KEY2 = "date long 2";

    private String adapterType;

    //date of set for search in DB
    private long longdate;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_modify_set, container, false);
        dBhelper = new DBhelper(getContext());
        updateButton = v.findViewById(R.id.buttonModifyUpdate);
        deleteButton = v.findViewById(R.id.buttonModifyDelete);
        cancelButton = v.findViewById(R.id.buttonModifyCancel);
        cancelButton = v.findViewById(R.id.buttonModifyCancel);
        viewWorkoutButton = v.findViewById(R.id.buttonViewWorkout);
        cancelButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
        updateButton.setOnClickListener(this);
        viewWorkoutButton.setOnClickListener(this);
        longdate = getArguments().getLong(CardRecyclerAdapter.KEY_LONG_DATE);
        exercise = getArguments().getString(CardRecyclerAdapter.KEY_EXERCISE);
        adapterType = getArguments().getString(CardRecyclerAdapter.KEY_CARD_TYPE);

        if (adapterType.equals(AdapterType.SETS_IN_A_DAY.name())) viewWorkoutButton.setVisibility(View.GONE);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonModifyCancel:
                dismiss();
                break;

            case R.id.buttonModifyDelete:
                dBhelper.deleteSetByDate(longdate);
                updateLists();
                dismiss();
                break;
            case R.id.buttonModifyUpdate:
                Intent intent = new Intent(getContext(), UpdateSetActivity.class);
                intent.putExtra(EXERCISE_KEY, exercise);
                intent.putExtra(EXERCISE_DATE_LONG_KEY1, longdate);
                startActivity(intent);
                dismiss();
                break;
            case R.id.buttonViewWorkout:
                Intent intent1 = new Intent(getContext(), ViewWorkoutActivity.class);
                intent1.putExtra(EXERCISE_DATE_LONG_KEY2, longdate);
                startActivity(intent1);
                dismiss();
        }


    }

    private void updateLists(){
        if (adapterType.equals(AdapterType.SET_OPTIONS_LIST)){
            ((NewSetActivity)getActivity()).getHistoryTabFragment().updateMainList();
        }
        else ((WorkoutFragment)getActivity().getSupportFragmentManager().findFragmentById(R.id.fragmentViewWorkout)).updateMainListView();

    }
}
