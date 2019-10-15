package com.n0153.fitnessnotes.dialogs;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.n0153.fitnessnotes.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RenameCategoryFragment extends DialogFragment {


    public RenameCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rename_category, container, false);
    }

}
