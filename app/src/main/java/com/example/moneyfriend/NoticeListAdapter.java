package com.example.moneyfriend;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class NoticeListAdapter extends BaseAdapter {

    private Context context;
    private List<ListItem> noticeList;

    public NoticeListAdapter(Context context, List<ListItem> noticeList) {
        this.context = context;
        this.noticeList = noticeList;
    }

    @Override
    public int getCount() {
        return noticeList.size();
    }

    @Override
    public Object getItem(int position) {
        return noticeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.listitem, null);
        TextView noticeTitle = (TextView) v.findViewById(R.id.listItem_noticeTitle);
        //TextView noticeWriter = (TextView) v.findViewById(R.id.listItem_noticeWriter);
        TextView noticeDate = (TextView) v.findViewById(R.id.listItem_noticeDate);

        noticeTitle.setText(noticeList.get(position).getTitle());
        //noticeWriter.setText(noticeList.get(position).getWriter());
        noticeDate.setText(noticeList.get(position).getDate());

        v.setTag(noticeList.get(position).getTitle());

        return v;
    }

}
