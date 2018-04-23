package com.example.lucas.aula06;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FeedReaderDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";
    private static  final String TEXT_TYPE = " TEXT";
    private static  final String COMMA_SEP = " ,";
    private static  final String SQL_CREATE_ENTRIES = " CREATE TABLE tbl_pais (id INTEGER PRIMARY KEY, data TEXT)";
    private static  final String SQL_DELETE_ENTRIES = " DROP TABLE IF EXISTS tbl_pais";

    public FeedReaderDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public  void onCreate(SQLiteDatabase db){
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }

}
