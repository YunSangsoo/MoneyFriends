package com.example.moneyfriend;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;
import static android.content.ContentValues.TAG;

public class Fragment_StudentInfo_teacher extends Fragment {


    private View view;
    private ListView studentInfoListView;
    private StudentInfoListAdapter adapter;
    private List<StudentInfoListItem> studentInfoList;

    private Button fresh_btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_studentinfo_teacher,container, false);
        studentInfoListView = (ListView) view.findViewById(R.id.studentInfoList);
        studentInfoList = new ArrayList<StudentInfoListItem>();
        fresh_btn = view.findViewById(R.id.btn_fresh2);


        data.db.getStudentList();

        fresh_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for(Student st : data.studentList ){
                    Log.d(TAG,"count now");
                    studentInfoList.add(new StudentInfoListItem(st.getName(),st.getAttendanceNumber(),st.getSalary(),st.getClassNumber(),st.getSchool(),st.getJob(),st.getCreditScore()));
                }

                adapter = new StudentInfoListAdapter(view.getContext().getApplicationContext(), studentInfoList);
                studentInfoListView.setAdapter(adapter);


                studentInfoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        StudentInfoListItem item = (StudentInfoListItem) adapter.getItem(position);

                        Intent intent = new Intent(getActivity(),OpenStudent_teacher.class);


                        intent.putExtra("Student_name", item.getName());
                        intent.putExtra("Student_classNum", item.getClassNumber());
                        intent.putExtra("Student_attendanceNum",item.getAttendanceNumber());
                        intent.putExtra("Student_salary", item.getBalance());
                        intent.putExtra("Student_job", item.getJob());
                        intent.putExtra("Student_creditScore",item.getcreditScore());
                        intent.putExtra("Student_school",item.getschool());

                        startActivity(intent);
                    }
                });
            }
        });


        //--------list view



        return view;
    }

}