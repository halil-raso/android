package com.halil.android.blogapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by halil on 14.02.2018.
 */



public class ArticlesAdapter extends RecyclerView.Adapter<RecyclerViewHolders>{

    private ArrayList<Article> itemList;
    private Context context;
    public static boolean flag =false;

    public ArticlesAdapter(Context context, ArrayList<Article> itemList) {

        this.itemList = itemList;
        this.context = context;

    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;

    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, final int position) {
        if(flag){
            holder.itemView.setSelected(false);
        }
        if(RecyclerViewHolders.selectedItems.contains(position)){
            holder.itemView.setSelected(true);
        }else{
            holder.itemView.setSelected(false);
        }
        holder.articleTitle.setText("Song Title: " + itemList.get(position).getTitle());
        holder.articleContent.setText("Song Year: " + itemList.get(position).getContent());
        holder.articleID.setText("Song Author: " + itemList.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}

