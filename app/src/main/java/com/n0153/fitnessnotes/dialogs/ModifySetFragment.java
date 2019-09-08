package com.n0153.fitnessnotes.dialogs;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.n0153.fitnessnotes.R;
import com.n0153.fitnessnotes.adapters.CardRecyclerAdapter;
import com.n0153.fitnessnotes.db_utils.DBhelper;
import com.n0153.fitnessnotes.fragments.HistoryTabFragment;


public class ModifySetFragment extends DialogFragment implements View.OnClickListener {

    private Button updateButton, deleteButton, cancelButton;
    private DBhelper dBhelper;

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
                dBhelper.deleteSetByDate(longdate);
                HistoryTabFragment.getInstance().updateMainList();
                dismiss();


        }


    }
}
