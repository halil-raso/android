package com.halil.android.blogapp;

/**
 * Created by halil on 14.02.2018.
 */

import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.TextView;

public class RecyclerViewHolders extends RecyclerView.ViewHolder
        implements View.OnClickListener{

    public TextView articleTitle;
    public TextView articleContent;
    public TextView articleID;

    private SparseBooleanArray selectedItems = new SparseBooleanArray();

    public RecyclerViewHolders(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        articleTitle = (TextView)itemView.findViewById(R.id.articleTitle);
        articleContent = (TextView)itemView.findViewById(R.id.articleContent);
        articleID = (TextView)itemView.findViewById(R.id.articleID);
    }

    @Override
    public void onClick(View view) {
        if (selectedItems.get(getAdapterPosition(), false)) {
            selectedItems.delete(getAdapterPosition());
            view.setSelected(false);
        }
        else {
            selectedItems.put(getAdapterPosition(), true);
            view.setSelected(true);
        }
    }
}