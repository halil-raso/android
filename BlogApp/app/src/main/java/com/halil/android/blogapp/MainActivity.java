package com.halil.android.blogapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    DBHelper articlesDB;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private ArticlesAdapter adapter;
    private ArrayList<Article> articlesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles_list);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));
        layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        articlesDB = new DBHelper(this);
        articlesList = articlesDB.getAllArticles();
        adapter = new ArticlesAdapter(MainActivity.this, articlesList);
        recyclerView.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menut, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.newAction:
                Intent newArticleIntent = new Intent(this, NewArticleActivity.class);
                startActivity(newArticleIntent);
                break;
            case R.id.emailAction:
                Intent mailIntent = new Intent(Intent.ACTION_SENDTO);
                String uriText = "mailto:" + Uri.encode("krashow@gmail.com") +
                        "?subject=" + Uri.encode("the subject") +
                        "&body=" + Uri.encode("the body of the message");
                Uri uri = Uri.parse(uriText);
                mailIntent.setData(uri);
                startActivity(Intent.createChooser(mailIntent, "Send mail via"));
                break;
            case R.id.webAction:
                Uri webpage = Uri.parse("http://www.android.com");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(webIntent);
                break;
            case R.id.callAction:
                Uri location = Uri.parse("tel:05061319005");
                Intent mapIntent = new Intent(Intent.ACTION_DIAL, location);
                startActivity(mapIntent);
                break;
            case R.id.removeAction:
                /*DBHelper mydb =  new DBHelper(this);
                String[] ids=
                        mydb.deleteArticle();
                Intent mapIntent = new Intent(Intent.ACTION_DIAL, location);
                startActivity(mapIntent);*/
        }
        return super.onOptionsItemSelected(item);

    }

}
