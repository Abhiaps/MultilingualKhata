package com.example.khaataa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText name,ph,pass,conf;
    Button signup;
    DatabaseH db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        name=findViewById(R.id.name);
        ph=findViewById(R.id.ph);
        pass=findViewById(R.id.pass);
        conf=findViewById(R.id.conf);
        signup=findViewById(R.id.signup);
        db=new DatabaseH(this);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String nm,phone,pa,con;
                nm=name.getText().toString();
                final String x=getString(R.string.inappropriate);
                final String y=getString(R.string.Registered);
                phone=ph.getText().toString();
                pa=pass.getText().toString();
                con=conf.getText().toString();
                if(nm.equals("")||phone.equals("")||pa.equals("")||con.equals(""))
                    Toast.makeText(Register.this,x, Toast.LENGTH_SHORT).show();
                else if(!(pa.equals(con)))
                {
                    Toast.makeText(Register.this, x, Toast.LENGTH_SHORT).show();

                }
                else{
                    boolean ck=db.insertData(pa,nm,phone);
                    if(ck==true)
                        Toast.makeText(Register.this, y, Toast.LENGTH_SHORT).show();
                    else Toast.makeText(Register.this, x, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
