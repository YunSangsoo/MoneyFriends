package com.example.moneyfriend;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.MetadataChanges;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DbMain {

    //Using on Test DB
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
     final FirebaseFirestore db=FirebaseFirestore.getInstance();

     void openAccount(String ownerOfAccount, String key ,boolean savingsOrNot) //계좌 개설 함수
     {
         mAuth.signInAnonymously();

         Account account = new Account(ownerOfAccount, key,"Account Number",0);
         if (savingsOrNot == true)
             db.collection("Info/Account/SavingsAccount").document(mAuth.getUid()).set(account);
         else
             db.collection("Info/Account/BankAccount").document(mAuth.getUid()).set(account);
     }





     void deposit(int amount, String savingsOrBank) // 입금 함수
    {
        final DocumentReference accountRef;

        accountRef = db.collection("Info/Account/"+savingsOrBank+"Account").document(mAuth.getUid());

        accountRef.update("balance", FieldValue.increment(amount));



        accountRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task)
            {
               DocumentSnapshot documentSnapshot= task.getResult();
                AccountLog log = new AccountLog(true,amount,Integer.valueOf(documentSnapshot.get("balance").toString()),LocalDate.now(), LocalTime.now());
                addAccountLog(log,savingsOrBank);
            }
        });
    }





    int withdraw(int amount, String savingsOrBank) // 출금 함수
    {
        DocumentReference accountRef;

        accountRef = db.collection("Info/Account/"+savingsOrBank+"Account").document(mAuth.getUid());

        accountRef.update("balance", FieldValue.increment(-amount));

        accountRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task)
            {
                DocumentSnapshot documentSnapshot= task.getResult();
                AccountLog log = new AccountLog(false,amount,Integer.valueOf(documentSnapshot.get("balance").toString()),LocalDate.now(), LocalTime.now());
                addAccountLog(log,savingsOrBank);
            }
        });

        return amount;
    }





    void addAccountLog(AccountLog log, String savingsOrBank) // 계좌에 거래내역(Log)을 추가하는 함수
    {
        db.collection("Info/Account/"+savingsOrBank+"Account/"+mAuth.getUid()+"/AccountDetails")
                .document("Log_"+log.getDateOfTransaction()+"_"+log.getTimeOfTransaction()).set(log);
    }






}