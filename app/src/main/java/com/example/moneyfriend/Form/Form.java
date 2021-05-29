package com.example.moneyfriend.Form;

import android.util.Log;

public class Form
{
    private String studentName; //학생 이름
    private int attendanceNumber; //학생 출석번호
    private String title; //폼의 제목
    private String contents; //폼의 내용

    public Form(String studentName, int attendanceNumber, String title, String contents)
    {
        this.studentName = studentName;
        this.attendanceNumber = attendanceNumber;
        this.title = title;
        this.contents = contents;
    }

    public String getStudentName() { return studentName; }
    public int getAttendanceNumber() { return attendanceNumber; }
    public String getTitle() { return title; }
    public String getContents() { return contents; }

    @Override
    public String toString()
    {
        return ":" +
                "studentName='" + studentName + '\'' +
                ", attendanceNumber=" + attendanceNumber +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\''
                ;
    }
}
