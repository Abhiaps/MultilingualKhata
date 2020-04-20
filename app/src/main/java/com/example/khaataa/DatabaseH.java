package com.example.khaataa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseH extends SQLiteOpenHelper {
    public static final String database="user.db";
    public static final String table="data";
    public static final String col2="password";
    public static final String col3="name";
    public static final String col5="phone";

    public DatabaseH(Context context) {
        super(context,database, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table data(password TEXT PRIMARY KEY,name TEXT,phone TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS data");
    onCreate(db);
    }
    public boolean insertData(String pwd, String name, String ph){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(col2,pwd);
        cv.put(col3,name);
        cv.put(col5,ph);
        long result=db.insert(table,null,cv);
        if(result==-1)
            return false;
        else
            return true;
    }
    public Cursor allData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from data",null);
        return res;
    }

    public void cleanData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table, null, null);
    }
}
