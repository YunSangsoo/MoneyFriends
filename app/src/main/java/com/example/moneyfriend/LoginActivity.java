package com.example.moneyfriend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login = findViewById(R.id.btn_login); //xml button 연결(시작 페이지에서 btn_start를 누르면 메인 페이지로 이동 할 수 있도록 버튼 구현)

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class); //intent (move page from StartActivity to MainActivity)

                startActivity(intent);//activity 이동 구문
                overridePendingTransition(0, 0); // 화면전환 시 애니메이션 제거를 위한 구문입니다.

                finish(); //start activity를 메모리에서 제거, 기기의 back 버튼을 눌러도 시작페이지로 다시 돌아오는 것을 방지하기 위함입니다.
            }
        });
    }
}