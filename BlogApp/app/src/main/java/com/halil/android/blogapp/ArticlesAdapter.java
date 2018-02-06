package com.halil.android.blogapp;

/**
 * Created by halil on 06.02.2018.
 */


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.MyViewHolder> {

    private List<Article> articlesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, content;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            content = (TextView) view.findViewById(R.id.content);

        }
    }


    public ArticlesAdapter(List<Article> moviesList) {
        this.articlesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.article_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Article movie = articlesList.get(position);
        holder.title.setText(movie.getTitle());
        holder.content.setText(movie.getContent());
    }

    @Override
    public int getItemCount() {
        return articlesList.size();
    }
}