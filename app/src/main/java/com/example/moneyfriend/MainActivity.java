package com.example.moneyfriend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneyfriend.Form.Form;
import com.example.moneyfriend.Form.JobApplicationForm;
import com.example.moneyfriend.Form.NewJobSuggestionForm;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;
    Object objectCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DbMain db = new DbMain();

        List<String> list=db.getJobList(100);
        List<String> listRule = db.getRuleList();
        List<Form> formList = db.getJobApplicationForms();
        List<Form> formListNew = db.getNewJobSuggestionForms();
      // double test = db.getTaxRate("양도세");

        db.getTaxRate(new GetObjectCallback<Double>() {
            @Override
            public void callback(Double object)
            {
                objectCallback=object;
            }
        },"양도세");

        //NewJobSuggestionForm form1 = new NewJobSuggestionForm("KWON", 24, "군인", "신청내용", "이유", 350 );
        //db.suggestNewJob(form1);
        //NewJobSuggestionForm form2 = new NewJobSuggestionForm("YOO", 25, "간호사", "신청내용", "이유", 400 );
        //db.suggestNewJob(form2);


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


                db.paySalary(1,"YOOHYEONSEOK");


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


                //db.addNotice("test100", editText.getText().toString());

                Toast.makeText(getApplicationContext(),objectCallback.toString(),Toast.LENGTH_SHORT).show();
               // textView.setText(result);

            }
        });

        Button buttonR = (Button) findViewById(R.id.buttonOneR);
        buttonR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                Rule rule1_1_2 = new Rule(1,1,2,"ex");
                Rule rule3_1_1 = new Rule(3,1,1, "ex");
                Rule rule2_2_2 = new Rule(2,2,2,"ex");
                //db.addRule(rule1_1_2);
                //db.addRule(rule3_1_1);
                //db.addRule(rule2_2_2);



                Toast.makeText(getApplicationContext(),listRule.toString(),Toast.LENGTH_SHORT).show();
                 */



                Toast.makeText(getApplicationContext(),String.valueOf(objectCallback),Toast.LENGTH_SHORT).show();

            }
        });

        Button buttonF = (Button) findViewById(R.id.buttonOneF);
        buttonF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Toast.makeText(getApplicationContext(),formListNew.toString(),Toast.LENGTH_SHORT).show();


            }
        });



    }
}