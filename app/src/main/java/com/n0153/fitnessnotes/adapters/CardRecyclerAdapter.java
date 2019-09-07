package com.n0153.fitnessnotes.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.n0153.fitnessnotes.NewSetActivity;
import com.n0153.fitnessnotes.R;
import com.n0153.fitnessnotes.db_utils.models.SetDataModel;
import com.n0153.fitnessnotes.dialogs.ModifySetFragment;

import java.util.ArrayList;

public class CardRecyclerAdapter extends RecyclerView.Adapter<CardRecyclerAdapter.CardViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    private ArrayList<SetDataModel> setsList;
    private static OnSetItemClickListener clickListener;
    private ItemClickListener itemClickListener;
    private ModifySetFragment modifySetFragment;
    public final static String KEY_LONG_DATE = "long_key";
    private final String MODIFY_DIALOG_TAG = "Modify dialog started";


    public ItemClickListener getItemClickListener() {
        return itemClickListener;
    }

    public CardRecyclerAdapter(Context context, ArrayList<SetDataModel> setsList) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.setsList = setsList;
        itemClickListener = new ItemClickListener();
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.history_card_listraw, viewGroup, false);
        return new CardViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder cardViewHolder, int i) {
        cardViewHolder.setInfo.setText(setsList.get(i).getSetText());
        cardViewHolder.dateInfo.setText(String.valueOf(setsList.get(i).getDate().getTime()));

    }

    @Override
    public int getItemCount() {
        return setsList.size();
    }

    class CardViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        TextView setInfo;
        TextView dateInfo;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            setInfo = itemView.findViewById(R.id.textViewSet);

            //hidden field to save date of each set
            dateInfo = itemView.findViewById(R.id.textViewSetDate);
            itemView.setLongClickable(true);
            itemView.setOnLongClickListener(this);
        }


        @Override
        public boolean onLongClick(View v) {
            clickListener.onItemLongClick(getAdapterPosition(), v);

            return false;
        }
    }

    public void setOnItemClickListener(OnSetItemClickListener clickListener) {
        CardRecyclerAdapter.clickListener = clickListener;
    }


    public interface OnSetItemClickListener {
        void onItemLongClick(int position, View v);
    }

    class ItemClickListener implements OnSetItemClickListener {


        @Override
        public void onItemLongClick(int position, View v) {
            TextView textView = v.findViewById(R.id.textViewSetDate);
            long dateLong = Long.parseLong(textView.getText().toString());

            // Create dialog to modify set and send date into it via Bundle
            modifySetFragment = new ModifySetFragment();
            Bundle args = new Bundle();
            args.putLong(KEY_LONG_DATE, dateLong);

            modifySetFragment.setArguments(args);
            modifySetFragment.show(((NewSetActivity)context).getSupportFragmentManager(), MODIFY_DIALOG_TAG );

        }


    }


}
