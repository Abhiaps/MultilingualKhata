package com.example.khaataa;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;



public class Customer_Login extends AppCompatActivity {
EditText pass,phone;
Button login;
ProgressDialog pd;
DatabaseH db;
Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer__login);
        getSupportActionBar().hide();
        pass=findViewById(R.id.pass);
        phone=findViewById(R.id.phone);
        login=findViewById(R.id.login);
        pd=new ProgressDialog(this);
        db=new DatabaseH(this);
        cursor=db.allData();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String ps=pass.getText().toString();
               final String ph=phone.getText().toString();

               cursor.moveToFirst();
               int num=0;
               if(cursor.getCount()!=0)
               while(true)
               {
                   if(ps.equals(cursor.getString(0))&&ph.equals(cursor.getString(2)))
                   {
                       num++;
                       Intent i=new Intent(Customer_Login.this,FirstActivity.class);
                       startActivity(i);
                       Common.key=ps;
                       break;
                   }
                   if(cursor.isLast())break;
                   cursor.moveToNext();
               }
               if(num==0)
                   Toast.makeText(Customer_Login.this,getString(R.string.notFound), Toast.LENGTH_SHORT).show();
            }
        });
    findViewById(R.id.signup).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i=new Intent(Customer_Login.this,Register.class);
            startActivity(i);
        }
    });
    }
    }