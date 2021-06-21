package com.example.moneyfriend;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.callback.Callback;

public class Fragment_board extends Fragment {

    private View view;
    private ListView noticeListView;
    private NoticeListAdapter adapter;
    private List<ListItem> noticeList;

    DbMain db = data.db;
    List<String> titleList = db.getNoticeList();

    Object objectCallback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable  Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_board,container, false);
        noticeListView = (ListView) view.findViewById(R.id.board_main);
        noticeList = new ArrayList<ListItem>();


        for(int i = 0; i < titleList.size(); i++){

            String noticeTitle = titleList.get(i);


            noticeList.add(new ListItem(noticeTitle, "실시간 연결에 문제가 있음"));
        }

        adapter = new NoticeListAdapter(view.getContext().getApplicationContext(), noticeList);
        noticeListView.setAdapter(adapter);


        //--------list view 구현, 날짜 받아오는 기능 추가 필요

        noticeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListItem item = (ListItem) adapter.getItem(position);

                Intent intent = new Intent(getActivity(),OpenNotice.class);

                db.getNotice(new GetObjectCallback<String>() {
                    @Override
                    public void callback(String object)
                    {
                        objectCallback=object;
                    }
                },item.getTitle());

                String Notice_Content = String.valueOf(objectCallback);


                intent.putExtra("Notice_title", item.getTitle());
                intent.putExtra("Notice_content", Notice_Content);

                //Toast.makeText(getContext(), "선택 : "+ item.getTitle(), Toast.LENGTH_SHORT).show();

                startActivity(intent);
            }
        });


        return view;
    }



}