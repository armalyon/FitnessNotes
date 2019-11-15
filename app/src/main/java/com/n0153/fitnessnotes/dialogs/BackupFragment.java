package com.n0153.fitnessnotes.dialogs;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.n0153.fitnessnotes.R;
import com.n0153.fitnessnotes.adapters.BackUpListAdapter;


public class BackupFragment extends DialogFragment {

     ListView backUpListView;


    public BackupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_backup, container, false);

        backUpListView = v.findViewById(R.id.backupDialogListView);
        BackUpListAdapter adapter = new BackUpListAdapter(getContext());
        backUpListView.setAdapter(adapter);

        return v;
    }

}
