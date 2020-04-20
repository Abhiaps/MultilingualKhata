package com.example.khaataa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RecordHelper extends SQLiteOpenHelper {
    public static final String database="userdata.db";
    public static final String table="data1";
    public static final String col2="password";
    public static final String col3="data";
    public static final String col4="date";
    public static final String col5="status";
    public static final String col6="Phone";
    public static final String col7="Amount";

    public RecordHelper(Context context) {
        super(context,database, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table data1(password TEXT ,data TEXT,date TEXT,status TEXT,Phone TEXT,Amount TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS data1");
        onCreate(db);
    }
    public boolean insertData(String pwd, String name,String date,String status,String Phone,String Amount){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(col2,pwd);
        cv.put(col3,name);
        cv.put(col4,date);
        cv.put(col5,status);
        cv.put(col6,Phone);
        cv.put(col7,Amount);
        long result=db.insert(table,null,cv);
        if(result==-1)
            return false;
        else
            return true;
    }
    public Cursor allData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from data1",null);
        return res;
    }

    public void cleanData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table, null, null);
    }
    public boolean deleteData(String name,String date,String amount,String status,String phone){
        SQLiteDatabase db=this.getWritableDatabase();
        String[] args={name,date,amount,status,phone};
        long result = db.delete(table,"password=? and date=? and Amount=? and status=? and Phone=?",args);
        if(result==-1)return false;
        else return true;
    }
}
