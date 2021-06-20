package com.example.moneyfriend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SignUpActivity extends AppCompatActivity {

    DbMain db = new DbMain();

    EditText school, // 학교
            studentName, // 학생 이름
            atndNumber, // 출석 번호
            classNumber; // 반

    Button btn_confirm; //확인 버튼


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btn_confirm = findViewById(R.id.btn_signUpConfirm);

        school = findViewById(R.id.etxt_signUpSchool);
        studentName = findViewById(R.id.etxt_signUpName);
        classNumber = findViewById(R.id.etxt_signUpclassNumber);
        atndNumber = findViewById(R.id.etxt_signUpAttendanceNumber);

        btn_confirm.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   if (school.getText().toString().length() == 0)
                       Toast.makeText(getApplicationContext(), "학교 이름을 입력해주세요.", Toast.LENGTH_SHORT).show();
                   if (studentName.getText().toString().length() == 0)
                       Toast.makeText(getApplicationContext(), "학생 이름을 입력해주세요.", Toast.LENGTH_SHORT).show();
                   if (classNumber.getText().toString().length() == 0)
                       Toast.makeText(getApplicationContext(), "반을 입력해주세요.", Toast.LENGTH_SHORT).show();
                   if (atndNumber.getText().toString().length() == 0)
                       Toast.makeText(getApplicationContext(), "출석 번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                   // 누락사항 체크 및 토스트 메세지 띄움

                   //if (school.getText().getClass().getTypeName());


              // Toast.makeText(getApplicationContext(), "text: "+ atndNumber.getText().getClass().getName(), Toast.LENGTH_SHORT).show();

/*
                   if(atndNumber.getText().getClass().getName() == "android.text.SpannableStringBuilder"){
                       Toast.makeText(getApplicationContext(), "text: "+ atndNumber.getText().getClass().getName(), Toast.LENGTH_SHORT).show();
                   }
                   else*/

                       if (school.getText().toString().length() != 0
                           && studentName.getText().toString().length() != 0
                           && classNumber.getText().toString().length() != 0
                           && atndNumber.getText().toString().length() != 0) {

                       Student student = new Student(studentName.getText().toString(),
                               Integer.parseInt(atndNumber.getText().toString()),
                               Integer.parseInt(classNumber.getText().toString()),
                               school.getText().toString());

                       db.signUp(student);

                           Intent intent2 = new Intent(SignUpActivity.this, LoginActivity.class);

                           startActivity(intent2);

                           finish();
                       //Toast.makeText(getApplicationContext(), "학교: "+ school.getText().toString(), Toast.LENGTH_SHORT).show();

                   }
               }
           }
        );

    }
}