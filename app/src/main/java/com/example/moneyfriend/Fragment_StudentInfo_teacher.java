package com.example.moneyfriend;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class Fragment_StudentInfo_teacher extends Fragment {


    private View view;
    private ListView studentInfoListView;
    private StudentInfoListAdapter adapter;
    private List<StudentInfoListItem> studentInfoList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_studentinfo_teacher,container, false);
        studentInfoListView = (ListView) view.findViewById(R.id.studentInfoList);
        studentInfoList = new ArrayList<StudentInfoListItem>();


        //test용 Item 생성 부분입니다.
        studentInfoList.add(new StudentInfoListItem("name1", 1, 3));
        studentInfoList.add(new StudentInfoListItem("na22me1", 2222, 3));
        studentInfoList.add(new StudentInfoListItem("nam22e1", 1, 311));
        studentInfoList.add(new StudentInfoListItem("na2me1", 1556, 3));
        studentInfoList.add(new StudentInfoListItem("name1", 1, 3));
        studentInfoList.add(new StudentInfoListItem("na22me1", 2222, 3));
        studentInfoList.add(new StudentInfoListItem("nam22e1", 1, 311));
        studentInfoList.add(new StudentInfoListItem("na2me1", 1556, 3));
        studentInfoList.add(new StudentInfoListItem("name1", 1, 3));
        studentInfoList.add(new StudentInfoListItem("na22me1", 2222, 3));
        studentInfoList.add(new StudentInfoListItem("nam22e1", 1, 311));
        studentInfoList.add(new StudentInfoListItem("na2me1", 1556, 3));
        studentInfoList.add(new StudentInfoListItem("name1", 1, 3));
        studentInfoList.add(new StudentInfoListItem("na22me1", 2222, 3));
        studentInfoList.add(new StudentInfoListItem("nam22e1", 1, 311));
        studentInfoList.add(new StudentInfoListItem("na2me1", 1556, 3));


        adapter = new StudentInfoListAdapter(view.getContext().getApplicationContext(), studentInfoList);
        studentInfoListView.setAdapter(adapter);


        //--------list view



        return view;
    }

}