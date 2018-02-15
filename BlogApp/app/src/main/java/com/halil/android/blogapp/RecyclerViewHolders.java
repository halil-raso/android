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
            if (UtilityClass.s.contains(getAdapterPosition())) {
                UtilityClass.s.remove(UtilityClass.s.indexOf(getAdapterPosition()));
                view.setSelected(false);
            } else {
                UtilityClass.s.add(getAdapterPosition());
                view.setSelected(true);
            }
        }
        if (flag && UtilityClass.s.size() == 0) {
            flag = false;
        }

    }

    @Override
    public boolean onLongClick(View view) {

        if (UtilityClass.s.size() == 0) {
            flag = true;
        }
        if (UtilityClass.s.contains(getAdapterPosition())) {
            UtilityClass.s.remove(UtilityClass.s.indexOf(getAdapterPosition()));
            view.setSelected(false);
        } else {
            UtilityClass.s.add(getAdapterPosition());
            view.setSelected(true);
        }
        if (flag && UtilityClass.s.size() == 0) {
            flag = false;
        }
        return true;

    }

}