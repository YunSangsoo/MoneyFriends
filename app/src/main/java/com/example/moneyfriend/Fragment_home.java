package com.example.moneyfriend;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

public class Fragment_home extends Fragment {
    DbMain db = new DbMain();
    List<String> NoticeTitleList = db.getNoticeList();

    private View view;
    TextView NoticeTitle_1, NoticeTitle_2, NoticeTitle_3; // 하단에 공지사항 프리뷰 부분의 Title 텍스트 뷰
    TextView NoticeDate_1, NoticeDate_2, NoticeDate_3; // 하단에 공지사항 프리뷰 부분의 Date 텍스트 뷰

    TextView user_name,user_job,user_information;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable  Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home,container, false);


        user_name = view.findViewById(R.id.value_userName);
        user_job = view.findViewById(R.id.value_userJob);
        user_information = view.findViewById(R.id.value_userInfomation);

        user_name.setText(data.student.getName());
        user_job.setText(data.student.getJob());
        String temp_information = data.student.getSchool()+" "+data.student.getClassNumber()+"반 "+data.student.getAttendanceNumber()+"번 ";
        user_information.setText(temp_information);



            if(NoticeTitleList.isEmpty() == false){//파이어 베이스 비동기 문제때문에 일단 이렇게 두겠음
                NoticeTitle_1 = view.findViewById(R.id.value_notice1Title);
                NoticeTitle_2 = view.findViewById(R.id.value_notice2Title);
                NoticeTitle_3 = view.findViewById(R.id.value_notice3Title);


                NoticeTitle_1.setText(NoticeTitleList.get(0));
                NoticeTitle_2.setText(NoticeTitleList.get(1));
                NoticeTitle_3.setText(NoticeTitleList.get(2));

                NoticeDate_1 = view.findViewById(R.id.value_notice1Date);
                NoticeDate_2 = view.findViewById(R.id.value_notice2Date);
                NoticeDate_3 = view.findViewById(R.id.value_notice3Date);
                //아직 함수가 없음, 구현 후 연결
            }
            else Toast.makeText(getContext(), "Loading 중",Toast.LENGTH_LONG).show();



        return view;
    }
}
