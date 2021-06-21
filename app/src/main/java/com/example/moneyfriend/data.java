package com.example.moneyfriend;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

public class data {

    static ArrayList<Job> jobList;
    static int isSignup;
    static Student student;
    static DbMain db;

    static SharedPreferences preferences;
    static SharedPreferences.Editor editor;

    static String email;


    static List<Notice> NoticeList;

    static List<Student> studentList;

    static Account Saccount,Baccount;

    static boolean isadmin;
    static Teacher admin;

}