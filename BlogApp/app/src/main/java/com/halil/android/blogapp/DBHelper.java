package com.halil.android.blogapp;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by halil on 06.02.2018.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "articles.db";
    public static final String ARTICLES_TABLE_NAME = "articles";
    public static final String ARTICLES_COLUMN_ID = "id";
    public static final String ARTICLES_COLUMN_TILTE = "title";
    public static final String ARTICLES_COLUMN_CONTENT = "content";

    private HashMap hp;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table articles " +
                        "(id integer primary key, title text, content text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS articles");
        onCreate(db);
    }

    public boolean insertArticle (String title, String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("content", content);
        db.insert("articles", null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from articles where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, ARTICLES_TABLE_NAME);
        return numRows;
    }

    public boolean updateArticle (Integer id, String title, String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("content", content);
        db.update("articles", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteArticle (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("articles",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<Article> getAllArticles() {
        ArrayList<Article> array_list = new ArrayList<Article>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from articles", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(new Article(res.getString(res.getColumnIndex(ARTICLES_COLUMN_ID)),res.getString(res.getColumnIndex(ARTICLES_COLUMN_TILTE)),res.getString(res.getColumnIndex(ARTICLES_COLUMN_CONTENT)));
            res.moveToNext();
        }
        return array_list;
    }
}
