package com.example.khaataa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class SeeData extends AppCompatActivity {
    RecyclerView dataRecycler;
    RecordHelper db;
    Cursor cursor;
    ArrayList<String> data;
    ArrayList<String> date;
    ArrayList<String> status;
    ArrayList<String> phone;
    ArrayList<String> amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_data);
        dataRecycler = findViewById(R.id.dataRecycler);
        getSupportActionBar().hide();
        date=new ArrayList<>();
        amount=new ArrayList<>();
        phone=new ArrayList<>();
        status=new ArrayList<>();
        dataRecycler.setLayoutManager(new LinearLayoutManager(SeeData.this, LinearLayoutManager.VERTICAL, false));
        db = new RecordHelper(this);
        cursor = db.allData();
        cursor.moveToFirst();
        data = new ArrayList<>();
        data.clear();
        if (cursor.getCount() != 0) {
            while (true) {
                if (cursor.getString(1).equals(Common.key))
                {data.add(cursor.getString(0));
                date.add(cursor.getString(2));
                amount.add(cursor.getString(5));
                phone.add(cursor.getString(4));
                status.add(cursor.getString(3));
                    }
                if (cursor.isLast()) break;
                else cursor.moveToNext();
            }
            dataRecycler.setAdapter(new DataAdapter(data,date,amount,status,phone,SeeData.this));
        }
    }
}
