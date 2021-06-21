package com.example.moneyfriend;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Fragment_home_teacher extends Fragment {
    DbMain db = data.db;
    List<String> NoticeTitleList = db.getNoticeList();

    private View view;
    TextView NoticeTitle_1, NoticeTitle_2, NoticeTitle_3,NoticeTitle_4; // 하단에 공지사항 프리뷰 부분의 Title 텍스트 뷰
    TextView NoticeDate_1, NoticeDate_2, NoticeDate_3,NoticeDate_4; // 하단에 공지사항 프리뷰 부분의 Date 텍스트 뷰

    TextView user_name,user_information;
    TextView stock_name,stock_price;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable  Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_teacher,container, false);

        user_name = view.findViewById(R.id.value_userJob);
        user_information = view.findViewById(R.id.value_userInfomation);

        user_name.setText(data.admin.getName());
        String temp_information = data.admin.getSchool()+" "+data.admin.getClassNumber()+"반 선생님";
        user_information.setText(temp_information);

        NoticeTitle_1 = view.findViewById(R.id.value_notice1Title);
        NoticeTitle_2 = view.findViewById(R.id.value_notice2Title);
        NoticeTitle_3 = view.findViewById(R.id.value_notice3Title);
        NoticeTitle_4 = view.findViewById(R.id.value_notice4Title);

        NoticeDate_1 = view.findViewById(R.id.value_notice1Date);
        NoticeDate_2 = view.findViewById(R.id.value_notice2Date);
        NoticeDate_3 = view.findViewById(R.id.value_notice3Date);
        NoticeDate_4 = view.findViewById(R.id.value_notice4Date);

        stock_name = view.findViewById(R.id.text_stockName);
        stock_price = view.findViewById(R.id.value_stockPrice);

        stock_name.setText("숭실적금");
        data.db.setInvestmentrate("숭실적금",stock_price);



        data.NoticeList = new ArrayList<>();

        ArrayList<ArrayList<TextView>> frame = new ArrayList<ArrayList<TextView>>();
        ArrayList<TextView> data1 = new ArrayList<TextView>();
        data1.add(NoticeTitle_1);
        data1.add(NoticeDate_1);
        data1.add(null);
        data1.add(null);

        ArrayList<TextView> data2 = new ArrayList<TextView>();
        data2.add(NoticeTitle_2);
        data2.add(NoticeDate_2);
        data2.add(null);
        data2.add(null);

        ArrayList<TextView> data3 = new ArrayList<TextView>();
        data3.add(NoticeTitle_3);
        data3.add(NoticeDate_3);
        data3.add(null);
        data3.add(null);

        ArrayList<TextView> data4 = new ArrayList<TextView>();
        data3.add(NoticeTitle_4);
        data3.add(NoticeDate_4);
        data3.add(null);
        data3.add(null);

        frame.add(data1);
        frame.add(data2);
        frame.add(data3);
        frame.add(data4);

        data.db.testNotice(frame);


        return view;
    }
}