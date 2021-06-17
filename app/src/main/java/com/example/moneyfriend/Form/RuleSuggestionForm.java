package com.example.moneyfriend.Form;

public class RuleSuggestionForm extends Form
{
    String[] ReasonList; // 제정/수정/삭제 이유


    public RuleSuggestionForm(String studentName, int attendanceNumber, String title, String contents)
    {
        super(studentName, attendanceNumber, title, contents); //이름, 출석번호, 제안하는 법이름, 설명
        ReasonList = new String[3];
    }

}
