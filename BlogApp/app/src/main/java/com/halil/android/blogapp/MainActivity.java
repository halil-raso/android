package com.halil.android.blogapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

                DBHelper mydb =  new DBHelper(this);
                String[] ids = new String[RecyclerViewHolders.selectedItems.size()];
                for(int i=0; i<RecyclerViewHolders.selectedItems.size(); i++){
                    ids[i] = mydb.getArticleFromPositon(RecyclerViewHolders.selectedItems.get(i)).getId();
                }

                for(int n=0; n<RecyclerViewHolders.selectedItems.size();n++){
                    RecyclerViewHolders.selectedItems.remove(n);
                }

                for (int j=0 ; j<ids.length; j++){
                    Article article = mydb.getArticle(ids[j]);
                    int index = -1;
                    for(int k=0; k<articlesList.size(); k++){
                        if (article.getId().equals(articlesList.get(k).getId())){
                            index = k;
                            break;
                        }
                    }
                    if(index!=-1){
                        articlesList.remove(index);
                        mydb.deleteArticle(ids[j]);
                    }
                    ArticlesAdapter.flag = true;
                    adapter.notifyDataSetChanged();
                }
                RecyclerViewHolders.selectedItems.clear();

        }
        return super.onOptionsItemSelected(item);

    }

}
