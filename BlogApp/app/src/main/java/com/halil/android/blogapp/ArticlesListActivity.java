package com.halil.android.blogapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ArticlesListActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> articles;
    DBHelper articlesDB;
    private ShareActionProvider share=null;
    private static ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles_list);
        share = new ShareActionProvider(this);
        listView= findViewById(R.id.article_list);
        articlesDB = new DBHelper(this);
        articles= articlesDB.getAllArticles();
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, articles);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // ListView Clicked item index
                int itemPosition     = position;
                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);
                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                        .show();
            }

        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menut,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.newAction:
                Intent newArticleIntent = new Intent(this,NewArticleActivity.class);
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
        }
        return super.onOptionsItemSelected(item);
    }

}
