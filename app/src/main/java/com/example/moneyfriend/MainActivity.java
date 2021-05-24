package com.example.moneyfriend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneyfriend.Form.Form;
import com.example.moneyfriend.Form.JobApplicationForm;

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

        List<String> list=db.getJobList(350);
        List<String> listRule = db.getRuleList();
        List<Form> formList = db.getJobApplicationForms();

        JobApplicationForm form = new JobApplicationForm("KWON", 24, "공란", "신청이유", "세무사", "수학1등급" );
        db.applyForJob(form);


        editText = (EditText) findViewById(R.id.edit_);
         textView = (TextView) findViewById(R.id.textview);



        //db.openAccount(1,"YOOHYEONSEOK",false);
        /*
        Button buttonD = (Button) findViewById(R.id.buttonOneD);
        buttonD.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) { db.deposit(1, "YOOHYEONSEOK",1000,"Bank"); }
        });

        Button buttonW = (Button) findViewById(R.id.buttonOneW);
        buttonW.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                db.withdraw(1, "YOOHYEONSEOK",500,"Bank");
            }
        });

         */

        Button buttonS = (Button) findViewById(R.id.buttonOneS);
        buttonS.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Student student =
                        new Student("YOOHYEONSEOK", 1, 5, "숭실초등학교");
                db.signUp(student);


            }
        });

        Button buttonJ = (Button) findViewById(R.id.buttonOneJ);
        buttonJ.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {





                Toast.makeText(getApplicationContext(),list.toString(),Toast.LENGTH_SHORT).show();


            }
        });

        Button buttonE = (Button) findViewById(R.id.buttonOneE);
        buttonE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {


                db.addNotice("test5", editText.getText().toString());
                String result=db.getNotice("test5");

                textView.setText(result);

            }
        });

        Button buttonR = (Button) findViewById(R.id.buttonOneR);
        buttonR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Rule rule1_1_2 = new Rule(1,1,2,"ex");
                Rule rule3_1_1 = new Rule(3,1,1, "ex");
                Rule rule2_2_2 = new Rule(2,2,2,"ex");
                //db.addRule(rule1_1_2);
                //db.addRule(rule3_1_1);
                //db.addRule(rule2_2_2);



                Toast.makeText(getApplicationContext(),listRule.toString(),Toast.LENGTH_SHORT).show();


            }
        });

        Button buttonF = (Button) findViewById(R.id.buttonOneF);
        buttonF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(getApplicationContext(),formList.toString(),Toast.LENGTH_SHORT).show();


            }
        });



    }
}