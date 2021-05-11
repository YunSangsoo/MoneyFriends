package com.example.moneyfriend;

public class Student
{
    private String name;
    private int attendanceNumber; //출석번호
    private int classNumber; //반
    private String school; //학교
    private String job; //직업
    private int salary;// 급여
    private int creditScore; // 신용점수
    private String studentKey; //학생 key값_must be locked
    private String accountKey; // 계좌 key값_must be locked

    private String uid;//UID

    public Student(String name, int attendanceNumber, int classNumber, String school, String job, int salary, int creditScore, String studentKey, String accountKey)
    {
        this.name = name;
        this.attendanceNumber = attendanceNumber;
        this.classNumber = classNumber;
        this.school = school;
        this.job = job;
        this.salary = salary;
        this.creditScore = creditScore;
        this.studentKey = studentKey;
        this.accountKey = accountKey;

        //uid
        this.uid = null;
    }

    public String getName() {
        return name;
    }

    public int getAttendanceNumber() {
        return attendanceNumber;
    }

    public int getClassNumber() {
        return classNumber;
    }

    public String getSchool() { return school; }

    public String getJob() { return job; }

    public int getSalary() { return salary; }

    public int getCreditScore () {return  creditScore; }

    public String getStudentKey() {
        return studentKey;
    }

    public String getAccountKey() {
        return accountKey;
    }

    public String getUid() { return uid;}


    //예시
    public int putuid(String puid){
        uid = puid;
        return 1;
    }
}
