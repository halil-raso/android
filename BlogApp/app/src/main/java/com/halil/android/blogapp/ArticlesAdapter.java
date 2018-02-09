package com.halil.android.blogapp;

/**
 * Created by halil on 06.02.2018.
 */


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.MyViewHolder> {

    private List<Article> articlesList;
    public boolean flag=false;
    HashMap<String, Boolean> selectedItems;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, content;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            content = (TextView) view.findViewById(R.id.content);
        }

    }


    public ArticlesAdapter(List<Article> articlesList) {
        this.articlesList = articlesList;
        selectedItems = new HashMap<>(articlesList.size());
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.article_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if(flag){
            holder.itemView.findViewById(R.id.checkBox).setVisibility(View.VISIBLE);
        }
        Article movie = articlesList.get(position);
        holder.title.setText(movie.getTitle());
        holder.content.setText(movie.getContent().split("\\n")[0]);
    }

    @Override
    public int getItemCount() {
        return articlesList.size();
    }
    public void setSelectedItem(String id, boolean f){
        selectedItems.
        if(selectedItems.containsKey(id)){
            selectedItems.replace(id,f);
        }
    }
}