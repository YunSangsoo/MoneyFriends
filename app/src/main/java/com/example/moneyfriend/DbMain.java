package com.example.moneyfriend;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DbMain {

    //Using on Test DB
    private FirebaseAuth mAuth;

    void TestDB() {

        final String TAG = "here";

        mAuth = FirebaseAuth.getInstance();

        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        mAuth.signInAnonymously();

        Map<String, Object> user = new HashMap<>();

        user.put("Field1", "Name");
        user.put("Field2","Phone Number");
        user.put("Number",119);

        Map<String, Object> test = new HashMap<>();

        test.put("test1","YOO1");
        test.put("test2","YOO2");
        test.put("test3","YOO3");


        db.document("/UserData/Temp").set(user);

        db.document("/UserData/YOO_Test_/Uid/"+mAuth.getUid()).set(test);

    }
}