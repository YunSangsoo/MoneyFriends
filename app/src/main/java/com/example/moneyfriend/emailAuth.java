package com.example.moneyfriend;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.Executor;

import static android.content.ContentValues.TAG;

public class emailAuth {


    private FirebaseAuth mAuth;

    private FirebaseUser curUser;


    public emailAuth() {
        mAuth = FirebaseAuth.getInstance();
    }

    public String getuid(){
        curUser = mAuth.getCurrentUser();
        Log.d(TAG, "my uid:" + curUser.getUid());
        return curUser.getUid();
    }


    public void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);

        mAuth.createUserWithEmailAndPassword(email,password);
        curUser = mAuth.getCurrentUser();
    }

    public void signIn(String email, String password) {
        int result = 0;
        Log.d(TAG, "signIn:" + email);
        mAuth.signInWithEmailAndPassword(email, password);
        curUser = mAuth.getCurrentUser();
    }
    public void sendEmailVerification() {

        // Send verification email
        final FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification();
    }

    public boolean isSignin(){
        if(curUser==null)
            return false;
        else
            return true;
    }
}
