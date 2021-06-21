package com.example.moneyfriend;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_investment extends Fragment {

    private View view;
    private TextView account,principal1,principal2,interestrate,expectedreceipt;
    String name = "숭실적금";
    @Nullable
    @Override


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable  Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_investment,container, false);

        account = view.findViewById(R.id.value_accountPeriod);
        principal1 = view.findViewById(R.id.value_principal);
        principal2 = view.findViewById(R.id.value_principal2);
        interestrate = view.findViewById(R.id.value_interestRate);
        expectedreceipt = view.findViewById(R.id.value_expectedReceipt);

        account.setText(name);

        data.db.setinvestment(name,principal1,principal2,interestrate,expectedreceipt);

        return view;






    }
}
