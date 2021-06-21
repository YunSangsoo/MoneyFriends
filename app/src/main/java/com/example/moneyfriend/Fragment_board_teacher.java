package com.example.moneyfriend;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Fragment_board_teacher extends Fragment {

    private View view;
    private ListView noticeListView;
    private NoticeListAdapter adapter;
    private List<ListItem> noticeList;

    DbMain db = data.db;
    List<String> titleList = db.getNoticeList();

    Object objectCallback;

    Button btn_write;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable  Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_board_teacher,container, false);
        noticeListView = (ListView) view.findViewById(R.id.board_main_teacher);
        noticeList = new ArrayList<ListItem>();
        btn_write = view.findViewById(R.id.writeNotice);


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

                Intent intent = new Intent(getActivity(),OpenNotice_teacher.class);

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

        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WriteNoticeActivity.class);

                startActivity(intent);
            }
        });


        return view;
    }


}