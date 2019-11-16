package com.n0153.fitnessnotes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.n0153.fitnessnotes.R;

public class BackUpListAdapter extends BaseAdapter {

    String backUpItemsArray [];
    private LayoutInflater inflater;
    Context context;

    public BackUpListAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        backUpItemsArray = context.getResources().getStringArray(R.array.backupItemsArray);
    }

    @Override
    public int getCount() {
        return backUpItemsArray.length;
    }

    @Override
    public Object getItem(int position) {
        return backUpItemsArray[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.listraw_backup, parent, false);
        ((TextView)convertView.findViewById(R.id.backupItemTextView)).setText(backUpItemsArray[position]);
        ImageView iconView = convertView.findViewById(R.id.backupIcon);

        switch (position){
            case 0:
               iconView.setImageDrawable(context.getDrawable(R.drawable.ic_folder_open_24dp));
               break;
            case 1:
                iconView.setImageDrawable(context.getDrawable(R.drawable.ic_cloud_upload_24dp));
                break;
            case 2:
                iconView.setVisibility(View.INVISIBLE);

        }




        return convertView;
    }
}
