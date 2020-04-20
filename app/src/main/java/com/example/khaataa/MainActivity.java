package com.example.khaataa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button en,ar,fr,bn,hi,kn,or,ta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        en=findViewById(R.id.en);
        ar=findViewById(R.id.ar);
        or=findViewById(R.id.or);
        bn=findViewById(R.id.bn);
        hi=findViewById(R.id.hi);
        kn=findViewById(R.id.kn);
        fr=findViewById(R.id.fr);
        ta=findViewById(R.id.ta);
        en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLocale("en");
                Intent i=new Intent(MainActivity.this,Customer_Login.class);
                startActivity(i);
            }
        });
        ar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLocale("ar");
                Intent i=new Intent(MainActivity.this,Customer_Login.class);
                startActivity(i);
            }
        });
        or.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLocale("or");
                Intent i=new Intent(MainActivity.this,Customer_Login.class);
                startActivity(i);
            }
        });
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLocale("bn");
                Intent i=new Intent(MainActivity.this,Customer_Login.class);
                startActivity(i);
            }
        });
        hi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLocale("hi");
                Intent i=new Intent(MainActivity.this,Customer_Login.class);
                startActivity(i);
            }
        });
        kn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLocale("kn");
                Intent i=new Intent(MainActivity.this,Customer_Login.class);
                startActivity(i);
            }
        });
        fr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLocale("fr");
                Intent i=new Intent(MainActivity.this,Customer_Login.class);
                startActivity(i);
            }
        });
        ta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLocale("ta");
                Intent i=new Intent(MainActivity.this,Customer_Login.class);
                startActivity(i);
            }
        });
    }

    private void setLocale(String s) {
        Locale locale=new Locale(s);
        Locale.setDefault(locale);
        Configuration config=new Configuration();
        config.locale=locale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor=getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My_Lang",s);
        editor.apply();
    }
    public void loadLocale(){
        SharedPreferences prefs=getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language=prefs.getString("My_Lang","");
        setLocale(language);
    }
}
