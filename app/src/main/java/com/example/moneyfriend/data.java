package com.example.moneyfriend;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

public class data {

    static int isSignup;
    static Student student;
    static DbMain db;

    static SharedPreferences preferences;
    static SharedPreferences.Editor editor;

    static String email;


    static List<String> NoticeTitleList;

    static Account Saccount,Baccount;

}