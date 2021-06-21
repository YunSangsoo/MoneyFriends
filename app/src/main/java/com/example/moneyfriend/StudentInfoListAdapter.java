package com.example.moneyfriend;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class StudentInfoListAdapter extends BaseAdapter {

    private Context context;
    private List<StudentInfoListItem> studentInfoList;

    public StudentInfoListAdapter(Context context, List<StudentInfoListItem> studentInfoList) {
        this.context = context;
        this.studentInfoList = studentInfoList;
    }

    @Override
    public int getCount() {
        return studentInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return studentInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.listitem_studentaccountinfo, null);
        TextView name = (TextView) v.findViewById(R.id.value_studentAccountInfo_name);
        TextView attendanceNumber = (TextView) v.findViewById(R.id.value_studentAccountInfo_attendanceNumber);
        TextView balance = (TextView) v.findViewById(R.id.value_studentAccountInfo_balance);

        int atndNum = Integer.parseInt(studentInfoList.get(position).getAttendanceNumber());
        int blnce = Integer.parseInt(studentInfoList.get(position).getBalance());


        name.setText(studentInfoList.get(position).getName());
        attendanceNumber.setText(Integer.toString(atndNum));
        balance.setText(Integer.toString(blnce));
        v.setTag(studentInfoList.get(position).getName());

        return v;
    }

}