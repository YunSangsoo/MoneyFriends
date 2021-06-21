
package com.example.moneyfriend;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.utils.Oscillator;

import java.util.ArrayList;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

public class OpenJob_teacher extends AppCompatActivity {
    TextView noticeName, noticeClass, noticeJob, noticeSalary, noticeCredit;

    Button editbtn,closebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_job_teacher);

        editbtn = findViewById(R.id.btn_editJob);
        closebtn = findViewById(R.id.btn_closejob);

        Intent secondIntent = getIntent();
        String JobName = secondIntent.getStringExtra("Job_Name");
        String JobSalary = secondIntent.getStringExtra("Job_Salary");

        noticeName = findViewById(R.id.value_openjobName);
        noticeName.setText(JobName);

        noticeClass = findViewById(R.id.value_openjobSalary);
        noticeClass.setText(" " + JobSalary+"미소");


        editbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder;
                AlertDialog dialog;
                builder = new AlertDialog.Builder(OpenJob_teacher.this);
                builder.setTitle("직업 수정");
                builder.setMessage("수정할 직업 이름을 작성해주세요");
                EditText inputName = new EditText(OpenJob_teacher.this);
                builder.setView(inputName);

                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder builder2;
                        AlertDialog dialog2;
                        builder2 = new AlertDialog.Builder(OpenJob_teacher.this);
                        builder2.setTitle("직업 수정");
                        builder2.setMessage("수정할 직업의 급여를 작성해주세요");
                        EditText inputSalary = new EditText(OpenJob_teacher.this);
                        inputSalary.setInputType(InputType.TYPE_CLASS_NUMBER);
                        builder2.setView(inputSalary);

                        builder2.setPositiveButton("확인", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Job edjb = new Job(inputName.getText().toString(),Integer.parseInt(inputSalary.getText().toString()),1);
                                data.db.editJob(JobName,edjb);
                                finish();
                            }
                        });
                        builder2.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Log.d(Oscillator.TAG,"cancel");
                            }
                        });

                        builder2.setCancelable(false);
                        dialog2 = builder2.create();
                        dialog2.show();
                    }
                });
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d(Oscillator.TAG,"cancel");
                    }
                });
                builder.setCancelable(false);
                dialog = builder.create();
                dialog.show();
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