package com.example.moneyfriend;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DbMain {

    //Using on Test DB
    private FirebaseAuth mAuth;

    void TestDB() {

        mAuth = FirebaseAuth.getInstance();

        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        mAuth.signInAnonymously();

        Map<String, Object> user = new HashMap<>();

        user.put("Field1", "Name");
        user.put("Field2","Phone Number");
        user.put("Number",119);

        db.document("/UserData/Temp").set(user);

    }
}