package com.example.moneyfriend;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DbMain {

    //Using on Test DB
    private FirebaseAuth mAuth;

    void TestDB() {

        final String TAG = "here!";

        mAuth = FirebaseAuth.getInstance();

        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        mAuth.signInAnonymously();

        AccountLog log1 = new AccountLog
                (true, 100, 100000, LocalDate.now() , LocalTime.now()
        );


        db.collection("/BankAccount/"+mAuth.getUid()+"/AccountDetails").document("LogList").set(log1);






    }
}