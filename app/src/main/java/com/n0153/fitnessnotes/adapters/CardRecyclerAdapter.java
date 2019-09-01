package com.n0153.fitnessnotes.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.n0153.fitnessnotes.R;

import java.util.ArrayList;

public class CardRecyclerAdapter extends RecyclerView.Adapter<CardRecyclerAdapter.CardViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    private ArrayList<String> textList;

    public CardRecyclerAdapter(Context context, ArrayList<String> textList) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.textList = textList;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.listraw_layout, viewGroup, false);
        return new CardViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder cardViewHolder, int i) {
        cardViewHolder.setInfo.setText(textList.get(i));

    }

    @Override
    public int getItemCount() {
        return textList.size();
    }

    class CardViewHolder extends RecyclerView.ViewHolder {
        TextView setInfo;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            setInfo = itemView.findViewById(R.id.textView2);

        }
    }

}
