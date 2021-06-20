package com.example.moneyfriend;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

public class Fragment_account extends Fragment {

    private View view;
    boolean checkAccount=false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable  Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_account,container, false);

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

            data.db.checkAccount(false);
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            data.db.updatebalance();
            super.onPostExecute(result);
            dialog.cancel();
        }
    }
}
