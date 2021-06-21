package com.example.moneyfriend;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class JobListAdapter extends BaseAdapter {

    private Context context;
    private List<JobListItem> jobList;

    public JobListAdapter(Context context, List<JobListItem> jobList) {
        this.context = context;
        this.jobList = jobList;
    }

    @Override
    public int getCount() {
        return jobList.size();
    }

    @Override
    public Object getItem(int position) {
        return jobList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.listitem_studentjob, null);
        TextView jobName = (TextView) v.findViewById(R.id.value_jobName);
        TextView salary = (TextView) v.findViewById(R.id.value_jobSalary);

        int sly = jobList.get(position).getSalary();


        jobName.setText(jobList.get(position).getJobName());
        salary.setText(Integer.toString(sly));//integer인 salary를 setText를 위해 String으로 바꿔줍니다.

        v.setTag(jobList.get(position).getJobName());

        return v;
    }

}
