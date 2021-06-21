
package com.example.moneyfriend;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

public class OpenNotice_teacher extends AppCompatActivity {
    DbMain db = data.db;
    Object objectCallback;
    TextView noticeTitle, noticeDate, NoticeContent;
    Button editbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_notice_teacher);

        Intent secondIntent = getIntent();
        String Notice_Title = secondIntent.getStringExtra("Notice_title");
        String Notice_Date = secondIntent.getStringExtra("Notice_date");
        String Notice_Content = secondIntent.getStringExtra("Notice_content");

        noticeTitle = findViewById(R.id.value_OpenNoticeTitle);
        noticeTitle.setText(Notice_Title);
        //게시글 열람시 보이는 창의 Title을 바꾸는 코드

        noticeDate = findViewById(R.id.value_OpenNoticeDate);
        noticeDate.setText(Notice_Date);
        //게시글 열람시 보이는 창의 Date를 바꾸는 코드

        NoticeContent = findViewById(R.id.value_OpenNoticeContent);
        NoticeContent.setText(Notice_Content);
        //게시글 열람시 보이는 창의 Content를 바꾸는 코드

        editbtn = findViewById(R.id.editNotice);

        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder;
                AlertDialog dialog;
                builder = new AlertDialog.Builder(OpenNotice_teacher.this);
                builder.setTitle("게시글 수정");
                builder.setMessage("수정할 내용을 적어주세요.");
                EditText inputcost = new EditText(OpenNotice_teacher.this);
                builder.setView(inputcost);

                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG,"input cost : " + inputcost.getText().toString());
                        Log.d(TAG,"modify message : " + Notice_Title + "\nand content : "+ inputcost.getText().toString());
                        data.db.modifyNotice(Notice_Title,inputcost.getText().toString());

                        data.NoticeList = new ArrayList<>();
                        data.db.testNotice(new ArrayList<ArrayList<TextView>>());
                        finish();
                    }
                });
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d(TAG,"cancel");
                    }
                });
                builder.setCancelable(false);
                dialog = builder.create();
                dialog.show();
            }
        });






        // Toast.makeText(getApplicationContext(), "extra : "+ secondIntent.getStringExtra("Notice_title") + "\ncontent\n" + objectCallback, Toast.LENGTH_SHORT).show();
    }
}