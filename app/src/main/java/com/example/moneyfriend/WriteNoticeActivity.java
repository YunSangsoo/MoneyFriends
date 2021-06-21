package com.example.moneyfriend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class WriteNoticeActivity extends AppCompatActivity {
    EditText title, content;
    Button btn_writeConfirm;

    DbMain db = data.db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_notice);

        title = findViewById(R.id.etxt_NoticeTitle);
        content = findViewById(R.id.etxt_NoticeContent);

        btn_writeConfirm = findViewById(R.id.editNoticeConfirm);

        btn_writeConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.addNotice(title.getText().toString(), content.getText().toString());

                data.NoticeList = new ArrayList<>();
                data.db.testNotice(new ArrayList<ArrayList<TextView>>());

                Intent intent = new Intent(WriteNoticeActivity.this, MainActivity.class);
                finish();
                Toast.makeText(getApplicationContext(),"게시 완료", Toast.LENGTH_SHORT).show();
            }
        });







    }
}