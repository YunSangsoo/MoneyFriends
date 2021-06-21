package com.example.moneyfriend;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.motion.utils.Oscillator;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class Fragment_Job_teacher extends Fragment {
    private View view;
    private ListView jobListView;
    private JobListAdapter adapter;
    private List<JobListItem> jobList;

    Button btn_addJob,btn_deleteJob,fresh_btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_job_teacher,container, false);
        jobListView = (ListView) view.findViewById(R.id.jobList);
        jobList = new ArrayList<JobListItem>();

        //버튼 연결입니다.
        btn_addJob = view.findViewById(R.id.btn_addJob); //직업 추가 버튼
        btn_deleteJob = view.findViewById(R.id.btn_deleteJob); //직업 제거 버튼
        fresh_btn = view.findViewById(R.id.btn_fresh3); // 새로고침 버튼


        data.jobList = new ArrayList<Job>();
        data.db.joblist();


        adapter = new JobListAdapter(view.getContext().getApplicationContext(), jobList);
        jobListView.setAdapter(adapter);

        fresh_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.db.joblist();

                for(Job jb : data.jobList ){
                    jobList.add(new JobListItem(jb.getName(),jb.getSalary()));
                }

                adapter = new JobListAdapter(view.getContext().getApplicationContext(), jobList);
                jobListView.setAdapter(adapter);

                jobListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        JobListItem item = (JobListItem) adapter.getItem(position);

                        Intent intent = new Intent(getActivity(),OpenJob_teacher.class);

                        intent.putExtra("Job_Name", item.getJobName());
                        intent.putExtra("Job_Salary", Integer.toString(item.getSalary()));

                        startActivity(intent);
                    }
                });
                data.jobList = new ArrayList<Job>();
            }
        });

        btn_addJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder;
                AlertDialog dialog;
                builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("직업 추가");
                builder.setMessage("추가할 직업 이름을 작성해주세요");
                EditText inputName = new EditText(getActivity());
                builder.setView(inputName);

                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder builder2;
                        AlertDialog dialog2;
                        builder2 = new AlertDialog.Builder(getActivity());
                        builder2.setTitle("직업 추가");
                        builder2.setMessage("추가할 직업의 급여를 작성해주세요");
                        EditText inputSalary = new EditText(getActivity());
                        inputSalary.setInputType(InputType.TYPE_CLASS_NUMBER);
                        builder2.setView(inputSalary);

                        builder2.setPositiveButton("확인", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Job addjb = new Job(inputName.getText().toString(),Integer.parseInt(inputSalary.getText().toString()),1);
                                        data.db.addJob(addjb);
                                    }
                        });
                        builder2.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Log.d(Oscillator.TAG,"cancel");
                            }
                        });

                        builder2.setCancelable(false);
                        dialog2 = builder2.create();
                        dialog2.show();
                    }
                });
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d(Oscillator.TAG,"cancel");
                    }
                });
                builder.setCancelable(false);
                dialog = builder.create();
                dialog.show();
            }
        });

        btn_deleteJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> jobname = new ArrayList<String>();
                for(Job nj : data.jobList){
                    jobname.add(nj.getName());
                }
                String[] jobarray = jobname.toArray(new String[jobname.size()]);

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setTitle("삭제할 직업을 골라주세요.");
                builder.setItems(jobarray, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int pos)
                    {
                        data.db.deleteJob(jobarray[pos]);
                    }
                });
                builder.setCancelable(false);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });



        return view;
    }

}