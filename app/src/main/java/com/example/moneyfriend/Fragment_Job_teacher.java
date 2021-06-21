package com.example.moneyfriend;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Job_teacher extends Fragment {
    private View view;
    private ListView jobListView;
    private JobListAdapter adapter;
    private List<JobListItem> jobList;

    Button btn_addJob, btn_editJob, btn_deleteJob;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_job_teacher,container, false);
        jobListView = (ListView) view.findViewById(R.id.jobList);
        jobList = new ArrayList<JobListItem>();

        //버튼 연결입니다.
        btn_addJob = view.findViewById(R.id.btn_addJob); //직업 추가 버튼
        btn_editJob = view.findViewById(R.id.btn_editJob); //직업 수정 버튼
        btn_deleteJob = view.findViewById(R.id.btn_deleteJob); //직업 제거 버튼




        //test용 Item 생성 부분입니다.
        jobList.add(new JobListItem("name1", 1));
        jobList.add(new JobListItem("nam22e1", 17778));
        jobList.add(new JobListItem("name221", 122556));
        jobList.add(new JobListItem("name1", 188888888));
        jobList.add(new JobListItem("name221", 111111));
        jobList.add(new JobListItem("na22me1", 1333));
        jobList.add(new JobListItem("name1", 19999));
        jobList.add(new JobListItem("na222me1", 122));
        jobList.add(new JobListItem("name1", 1));
        jobList.add(new JobListItem("nam22e1", 17778));
        jobList.add(new JobListItem("name221", 122556));
        jobList.add(new JobListItem("name1", 188888888));
        jobList.add(new JobListItem("name221", 111111));
        jobList.add(new JobListItem("na22me1", 1333));
        jobList.add(new JobListItem("name1", 19999));
        jobList.add(new JobListItem("na222me1", 122));
        jobList.add(new JobListItem("name1", 1));
        jobList.add(new JobListItem("nam22e1", 17778));
        jobList.add(new JobListItem("name221", 122556));
        jobList.add(new JobListItem("name1", 188888888));
        jobList.add(new JobListItem("name221", 111111));
        jobList.add(new JobListItem("na22me1", 1333));
        jobList.add(new JobListItem("name1", 19999));
        jobList.add(new JobListItem("na222me1", 122));







        btn_addJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext().getApplicationContext(), "add Button", Toast.LENGTH_SHORT).show();
            }
        });

        btn_editJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext().getApplicationContext(), "edit Button", Toast.LENGTH_SHORT).show();
            }
        });

        btn_deleteJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext().getApplicationContext(), "delete Button", Toast.LENGTH_SHORT).show();
            }
        });





        adapter = new JobListAdapter(view.getContext().getApplicationContext(), jobList);
        jobListView.setAdapter(adapter);


        //--------list view



        return view;
    }

}