package com.n0153.fitnessnotes.dialogs;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.n0153.fitnessnotes.R;
import com.n0153.fitnessnotes.adapters.CardRecyclerAdapter;


public class ModifySetFragment extends DialogFragment implements View.OnClickListener {

    private Button updateButton, deleteButton, cancelButton;

    //date of set for search in DB
    private long longdate;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_modify_set, container, false);
        updateButton = v.findViewById(R.id.buttonModifyUpdate);
        deleteButton = v.findViewById(R.id.buttonModifyDelete);
        cancelButton = v.findViewById(R.id.buttonModifyCancel);
        cancelButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
        updateButton.setOnClickListener(this);
        longdate = getArguments().getLong(CardRecyclerAdapter.KEY_LONG_DATE);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonModifyCancel:
                dismiss();
                break;

            case R.id.buttonModifyDelete:



        }


    }
}
