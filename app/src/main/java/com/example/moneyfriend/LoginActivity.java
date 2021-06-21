package com.example.moneyfriend;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import static android.content.ContentValues.TAG;


public class LoginActivity extends AppCompatActivity {

    Button btn_login, btn_signIn;
    EditText email,password;
    String i_email,i_password;

    AlertDialog.Builder builder;
    AlertDialog dialog;

    boolean check_admin;
    CheckBox chk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //edit
        data.preferences = PreferenceManager.getDefaultSharedPreferences(this);
        data.editor = data.preferences.edit();

        setContentView(R.layout.activity_login);

        btn_login = findViewById(R.id.btn_login); //xml button 연결(시작 페이지에서 btn_start를 누르면 메인 페이지로 이동 할 수 있도록 버튼 구현)
        btn_signIn = findViewById(R.id.btn_signUp);

        email = (EditText)findViewById(R.id.etxt_ID);
        password = (EditText)findViewById(R.id.etxt_PWD);

        chk = findViewById(R.id.chkBox1);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i_email = email.getText().toString();
                i_password = password.getText().toString();
                check_admin = chk.isChecked();

                asyncT asynct = new asyncT();
                asynct.execute();

                Intent intent = new Intent(LoginActivity.this, MainActivity.class); //intent (move page from StartActivity to MainActivity)


                //startActivity(intent);//activity 이동 구문
                //overridePendingTransition(0, 0); // 화면전환 시 애니메이션 제거를 위한 구문입니다.

                //finish(); //start activity를 메모리에서 제거, 기기의 back 버튼을 눌러도 시작페이지로 다시 돌아오는 것을 방지하기 위함입니다.
            }
        });

        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(LoginActivity.this, SignUpActivity.class);

                startActivity(intent2);
            }
        });


    }


    public class asyncT extends AsyncTask<Void,Void,String> {

        @Override
        protected void onPreExecute(){

            super.onPreExecute();

            builder = new AlertDialog.Builder(LoginActivity.this);
            builder.setTitle("wait"); builder.setMessage("Loading");
            builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.setCancelable(false);
            dialog = builder.create();
            dialog.show();

        }
        @Override
        protected String doInBackground(Void... params){
            data.email = i_email;
            Log.d(TAG,"login information : "+i_email+" "+i_password);
            data.db = new DbMain(i_email,i_password,check_admin);

            if(!check_admin){
                data.isadmin=false;
                data.db.loadUserInform(data.email,LoginActivity.this);
            }
            else{
                data.isadmin=true;
                data.db.loadAdminInform(data.email,i_password,LoginActivity.this);
            }
            return null;
        }
        @Override
        protected void onPostExecute(String result){
            dialog.cancel();
            super.onPostExecute(result);
            //dialog.cancel();
        }


    }

}