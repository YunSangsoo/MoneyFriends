package com.example.moneyfriend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

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
}