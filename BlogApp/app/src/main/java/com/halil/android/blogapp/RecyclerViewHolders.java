package com.halil.android.blogapp;

/**
 * Created by halil on 14.02.2018.
 */

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewHolders extends RecyclerView.ViewHolder
        implements View.OnClickListener, View.OnLongClickListener {

    public static boolean flag = false;
    public static ArrayList<Integer> selectedItems = new ArrayList<>();
    public TextView articleTitle;
    public TextView articleContent;
    public TextView articleID;

    public RecyclerViewHolders(View itemView) {

        super(itemView);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        articleTitle = (TextView) itemView.findViewById(R.id.articleTitle);
        articleContent = (TextView) itemView.findViewById(R.id.articleContent);
        articleID = (TextView) itemView.findViewById(R.id.articleID);

    }

    @Override
    public void onClick(View view) {

        if (flag) {
            if (RecyclerViewHolders.selectedItems.contains(getAdapterPosition())) {
                RecyclerViewHolders.selectedItems.remove(RecyclerViewHolders.selectedItems.indexOf(getAdapterPosition()));
                view.setSelected(false);
            } else {
                RecyclerViewHolders.selectedItems.add(getAdapterPosition());
                view.setSelected(true);
            }
        }
        if (flag && RecyclerViewHolders.selectedItems.size() == 0) {
            flag = false;
        }

    }

    @Override
    public boolean onLongClick(View view) {

        if (RecyclerViewHolders.selectedItems.size() == 0) {
            flag = true;
        }
        if (RecyclerViewHolders.selectedItems.contains(getAdapterPosition())) {
            RecyclerViewHolders.selectedItems.remove(RecyclerViewHolders.selectedItems.indexOf(getAdapterPosition()));
            view.setSelected(false);
        } else {
            RecyclerViewHolders.selectedItems.add(getAdapterPosition());
            view.setSelected(true);
        }
        if (flag && RecyclerViewHolders.selectedItems.size() == 0) {
            flag = false;
        }
        return true;

    }

}