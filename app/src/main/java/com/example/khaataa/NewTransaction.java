package com.example.khaataa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewTransaction extends AppCompatActivity {
RecordHelper db;
EditText Phone,status,data,Amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_transaction);
        getSupportActionBar().hide();
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        final String formattedDate = df.format(c);
        Amount=findViewById(R.id.Amount);
        Phone=findViewById(R.id.Phone);
        status=findViewById(R.id.status);
        data=findViewById(R.id.data);
        db=new RecordHelper(this);
        findViewById(R.id.record).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               boolean ck= db.insertData(data.getText().toString(),Common.key,formattedDate,status.getText().toString(),Phone.getText().toString(),Amount.getText().toString());
               if(ck==true)
                Toast.makeText(NewTransaction.this, getString(R.string.Registered), Toast.LENGTH_SHORT).show();
               else Toast.makeText(NewTransaction.this, getString(R.string.inappropriate), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
