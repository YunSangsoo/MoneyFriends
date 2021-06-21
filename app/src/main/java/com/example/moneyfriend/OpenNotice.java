package com.example.moneyfriend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class OpenNotice extends AppCompatActivity {
    DbMain db = data.db;
    Object objectCallback;
    TextView noticeTitle, noticeDate, NoticeContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_notice);

        Intent secondIntent = getIntent();
        String Notice_Title = secondIntent.getStringExtra("Notice_title");
        String Notice_Content = secondIntent.getStringExtra("Notice_content");

        noticeTitle = findViewById(R.id.value_OpenNoticeTitle);
        noticeTitle.setText(Notice_Title);
        //게시글 열람시 보이는 창의 Title을 바꾸는 코드

        noticeDate = findViewById(R.id.value_OpenNoticeDate);
        noticeDate.setText(Notice_Title);
        //게시글 열람시 보이는 창의 Date를 바꾸는 코드

        NoticeContent = findViewById(R.id.value_OpenNoticeContent);
        NoticeContent.setText(Notice_Content);
        //게시글 열람시 보이는 창의 Content를 바꾸는 코드






       // Toast.makeText(getApplicationContext(), "extra : "+ secondIntent.getStringExtra("Notice_title") + "\ncontent\n" + objectCallback, Toast.LENGTH_SHORT).show();
    }
}