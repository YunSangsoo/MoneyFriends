package com.example.moneyfriend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.time.LocalDate;
import java.time.LocalTime;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DbMain db = new DbMain();

        db.openAccount("YOO","key_1",false);

        Button buttonD = (Button) findViewById(R.id.buttonOneD);
        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deposit(10000, "Bank");
            }
        });

        Button buttonW = (Button) findViewById(R.id.buttonOneW);
        buttonW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.withdraw(5000, "Bank");
            }
        });










        //db.deposit(1000, "Bank");


       // db.withdraw(100, "Bank");


    }
}