package com.halil.android.blogapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;


public class NewArticleActivity extends AppCompatActivity implements View.OnClickListener {
    Button insertButton;
    EditText titleEditText;
    EditText contentEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_article_layout);
        insertButton = (Button) findViewById(R.id.insert_article_btn);
        titleEditText =  (EditText) findViewById(R.id.article_title);
        contentEditText =  (EditText) findViewById(R.id.article_content);
        insertButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        DBHelper mydb = new DBHelper(this);
        String title = titleEditText.getText().toString();
        String content = contentEditText.getText().toString();
        mydb.insertArticle(title,content);
        ArrayList<String> articles = mydb.getAllArticles();
        Log.d("JOJO",""+articles.size());
        Intent showAllArticlesIntent = new Intent(this,ArticlesListActivity.class);
        startActivity(showAllArticlesIntent);

    }
}
