
package com.example.moneyfriend;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class OpenStudent_teacher extends AppCompatActivity {
    TextView noticeName, noticeClass, noticeJob, noticeSalary, noticeCredit;

    Button closebtn,changejobbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_student_teacher);

        closebtn = findViewById(R.id.btn_close);
        changejobbtn = findViewById(R.id.btn_changejob);

        Intent secondIntent = getIntent();
        String Name = secondIntent.getStringExtra("Student_name");
        String ClassNum = secondIntent.getStringExtra("Student_classNum");
        String AttendNum = secondIntent.getStringExtra("Student_attendanceNum");
        String salary = secondIntent.getStringExtra("Student_salary");
        String job = secondIntent.getStringExtra("Student_job");
        String creditscore = secondIntent.getStringExtra("Student_creditScore");
        String school = secondIntent.getStringExtra("Student_school");
        String email = secondIntent.getStringExtra("email");

        noticeName = findViewById(R.id.value_openstudentName);
        noticeName.setText(Name);

        noticeClass = findViewById(R.id.value_studentClass);
        noticeClass.setText(school + " " + ClassNum + "반 " + AttendNum + "번");

        noticeJob = findViewById(R.id.value_openJob);
        noticeJob.setText("직업 : " + job);

        noticeSalary = findViewById(R.id.value_openSalary);
        noticeSalary.setText("보유 금액 : " + salary);

        noticeCredit = findViewById(R.id.value_openCreditScore);
        noticeCredit.setText("신용점수 : "+creditscore+"점");


        changejobbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> jobname = new ArrayList<String>();
                for(Job nj : data.jobList){
                    jobname.add(nj.getName());
                }
                String[] jobarray = jobname.toArray(new String[jobname.size()]);

                AlertDialog.Builder builder = new AlertDialog.Builder(OpenStudent_teacher.this);

                builder.setTitle("변경해줄 직업을 선택해주세요.");
                builder.setItems(jobarray, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int pos)
                    {
                        data.db.changejob(jobarray[pos],email);
                        finish();
                    }
                });
                builder.setCancelable(false);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        closebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });






        // Toast.makeText(getApplicationContext(), "extra : "+ secondIntent.getStringExtra("Notice_title") + "\ncontent\n" + objectCallback, Toast.LENGTH_SHORT).show();
    }
}