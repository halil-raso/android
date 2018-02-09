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
    private String articleId = "";
    private String articleTitle = "";
    private String articleContent = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_article_layout);
        Intent intent = getIntent();
        insertButton = findViewById(R.id.insert_article_btn);
        titleEditText = findViewById(R.id.article_title);
        contentEditText = findViewById(R.id.article_content);
        insertButton.setOnClickListener(this);
        if(intent.hasExtra("ARTICLE_ID")){
            articleId = intent.getStringExtra("ARTICLE_ID");
            articleTitle = intent.getStringExtra("ARTICLE_TITLE");
            articleContent = intent.getStringExtra("ARTICLE_CONTENT");
            titleEditText.setText(articleTitle);
            contentEditText.setText(articleContent);
        }
    }

    @Override
    public void onClick(View view) {
        DBHelper mydb = new DBHelper(this);
        String title = titleEditText.getText().toString();
        String content = contentEditText.getText().toString();
        if(articleId.equals("")){
            mydb.insertArticle(title, content);
            Log.d("halil","insert new article: "+content);
        } else{
            mydb.updateArticle(articleId,title,content);
            Log.d("halil","update with new content:"+content);
        }
        Intent showAllArticlesIntent = new Intent(this, ArticlesListActivity.class);
        startActivity(showAllArticlesIntent);


    }

}
