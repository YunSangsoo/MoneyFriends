package com.example.moneyfriend;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

public class Fragment_account extends Fragment {

    private View view;
    boolean checkAccount=false;
    private TextView credit_value,credit_text;

    private Button deposit_btn,withdraw_btn;

    public TextView account_Num,account_salary;
    public TextView Saccount_Num,Saccount_salary;
    public TextView accountlog1,accountlog2,accountlog3;
    public TextView Saccount_rate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable  Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_account,container, false);


        credit_value = view.findViewById(R.id.value_creditValue);
        credit_text = view.findViewById(R.id.value_creditRank);
        credit_value.setText(Integer.toString(data.student.getCreditScore()) + " 점");
        credit_text.setText("내 신용점수는 " +Integer.toString(data.student.getCreditScore())+"점 이에요");

        account_Num = view.findViewById(R.id.value_accountFrag_accountNumber);
        account_salary = view.findViewById(R.id.value_accountFrag_accountBalance);

        Saccount_Num = view.findViewById(R.id.value_accountFrag_installmentNumber);
        Saccount_salary = view.findViewById(R.id.value_accountFrag_InstallmentBalance);

        accountlog1 = view.findViewById(R.id.etxt_accountLog1);
        accountlog2 = view.findViewById(R.id.etxt_accountLog2);
        accountlog3 = view.findViewById(R.id.etxt_accountLog3);

        Saccount_rate = view.findViewById(R.id.value_accountFrag_installmentInterestRate);

        ArrayList<TextView> frame = new ArrayList<TextView>();
        frame.add(accountlog1);
        frame.add(accountlog2);
        frame.add(accountlog3);

        Log.d(TAG,"check here");
        data.db.testAccountlog(frame);

        data.db.setInvestmentrate("숭실적금",Saccount_rate);



        deposit_btn = view.findViewById(R.id.btn_deposit);

        deposit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder;
                AlertDialog dialog;
                builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("입금하기");
                builder.setMessage("적금 통장에 입금할 금액을 입력해주세요.");
                EditText inputcost = new EditText(getActivity());
                builder.setView(inputcost);
                inputcost.setInputType(5);

                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG,"input cost : " + inputcost.getText().toString());
                        data.db.deposit(data.student.getAttendanceNumber(),data.student.getName(),Integer.parseInt(inputcost.getText().toString()),"Savings");
                        data.db.withdraw(data.student.getAttendanceNumber(),data.student.getName(),Integer.parseInt(inputcost.getText().toString()),"Bank");
                        data.db.testAccountlog(frame);
                    }
                });
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d(TAG,"cancel");
                    }
                });
                builder.setCancelable(false);
                dialog = builder.create();
                dialog.show();

            }
        });

        Fragment_account.asyncT asynct = new Fragment_account.asyncT();
        asynct.execute();

        return view;
    }


    public class asyncT extends AsyncTask<Void,Void,String> {

        AlertDialog.Builder builder;
        AlertDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("wait");
            builder.setMessage("Loading");
            builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.setCancelable(false);
            dialog = builder.create();
            dialog.show();

        }

        @Override
        protected String doInBackground(Void... params) {

            data.db.checkAccount(false,account_Num,account_salary);
            data.db.checkAccount(true,Saccount_Num,Saccount_salary);
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            //data.db.updatebalance();
            super.onPostExecute(result);
            dialog.cancel();
        }
    }
}
