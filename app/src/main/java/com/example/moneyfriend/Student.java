package com.example.moneyfriend;

public class Student
{
    private String name;
    private String attendanceNumber; //출석번호
    private String classNumber; //반
    private String school; //학교
    private String studentKey; //학생 key값_must be locked
    private String accountKey; // 계좌 key값_must be locked

    public Student(String name, String attendanceNumber, String classNumber, String school, String studentKey, String accountKey)
    {
        this.name = name;
        this.attendanceNumber = attendanceNumber;
        this.classNumber = classNumber;
        this.school = school;
        this.studentKey = studentKey;
        this.accountKey = accountKey;
    }

    public String getName() {
        return name;
    }

    public String getAttendanceNumber() {
        return attendanceNumber;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public String getSchool() {
        return school;
    }

    public String getStudentKey() {
        return studentKey;
    }

    public String getAccountKey() {
        return accountKey;
    }
}
