package com.halil.android.blogapp;

/**
 * Created by halil on 06.02.2018.
 */

public class Article {
    private String id,title, content;

    public Article() {
    }

    public Article(String id,String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}