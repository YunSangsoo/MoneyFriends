package com.example.moneyfriend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DbMain db = new DbMain();

        List<String> list=db.getJobList();

         editText = (EditText) findViewById(R.id.edit_);
         textView = (TextView) findViewById(R.id.textview);



        db.openAccount(1,"YOOHYEONSEOK",false);

        Button buttonD = (Button) findViewById(R.id.buttonOneD);
        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { db.deposit(1, "YOOHYEONSEOK",1000,"Bank"); }
        });

        Button buttonW = (Button) findViewById(R.id.buttonOneW);
        buttonW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.withdraw(1, "YOOHYEONSEOK",500,"Bank");
            }
        });

        Button buttonS = (Button) findViewById(R.id.buttonOneS);
        buttonS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student =
                        new Student("YOOHYEONSEOK", 1, 5, "숭실초등학교");
                db.signUp(student);


            }
        });

        Button buttonJ = (Button) findViewById(R.id.buttonOneJ);
        buttonJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Job job1 = new Job("테스트직업3", 3000, 750);
                Job job2 = new Job("테스트직업4", 3000, 750);

                db.addJob(job1);
                db.addJob(job2);

                Toast.makeText(getApplicationContext(),list.toString(),Toast.LENGTH_SHORT).show();


            }
        });

        Button buttonE = (Button) findViewById(R.id.buttonOneE);
        buttonE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //db.saveNotice("test4", editText.getText().toString());
                String result=db.getNotice("test5");

                textView.setText(result);

            }
        });

        Button buttonR = (Button) findViewById(R.id.buttonOneR);
        buttonR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String,String> map = new HashMap<>();
                Map<String,String> map_ = new HashMap<>();
                map.put("Rule2","너는 못 지나간다");
                db.saveRule(map);

                //map_ = db.getRule("Rule1");

               // Toast.makeText(getApplicationContext(),map_.get("Rule1"),Toast.LENGTH_SHORT).show();

                db.setJob(1,"YOOHYEONSEOK","경찰");
                //db.paySalary(1,"YOOHYEONSEOK");


            }
        });



    }
}