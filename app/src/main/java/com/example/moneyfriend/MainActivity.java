package com.example.moneyfriend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView topNavigationView;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private Fragment_home fragment_home;
    private Fragment_account fragment_account;
    private Fragment_investment fragment_investment;
    private Fragment_board fragment_board;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topNavigationView = findViewById(R.id.topNavi);
        topNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.action_home:
                        setFrag(0);
                        break;
                    case R.id.action_account:
                        setFrag(1);
                        break;
                    case R.id.action_investment:
                        setFrag(2);
                        break;
                    case R.id.action_board:
                        setFrag(3);
                        break;
                }

                return true;
            }
        });

        fragment_home = new Fragment_home();
        fragment_account = new Fragment_account();
        fragment_investment = new Fragment_investment();
        fragment_board = new Fragment_board();

        setFrag(0);//초기 화면에 어떤 프레그먼트를 띄울지

    }

    @Override
    protected void onStart(){


        TextView title = findViewById(R.id.value_mainTitle);
        title.setText(data.student.getSchool()+" "+data.student.getClassNumber()+"반 ");

        super.onStart();
    }



    private void setFrag(int n){
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();


        switch (n){
            case 0:
                ft.replace(R.id.main_frame, fragment_home);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.main_frame, fragment_account);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.main_frame, fragment_investment);
                ft.commit();
                break;
            case 3:
                ft.replace(R.id.main_frame, fragment_board);
                ft.commit();
                break;
        }
    }

    public class asyncT extends AsyncTask<Void,Void,String> {

        AlertDialog.Builder builder;
        AlertDialog dialog;
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            builder = new AlertDialog.Builder(MainActivity.this);
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

            if(data.isSignup==1){
                String name = data.preferences.getString("name","이름없음");
                int attendanceNumber = data.preferences.getInt("attendanceNumber",-1);
                //data.db.loadUserInform(name,attendanceNumber);
            }

            return null;
        }
        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);

            TextView title = findViewById(R.id.value_mainTitle);
            title.setText(data.student.getSchool()+" "+data.student.getClassNumber()+"반 ");
            dialog.cancel();
        }


    }
}