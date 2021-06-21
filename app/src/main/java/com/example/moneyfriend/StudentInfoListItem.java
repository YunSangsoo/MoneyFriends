package com.example.moneyfriend;
//공지사항 리스트 뷰에 들어가는 리스트 아이템을 위한 클래스 파일입니다.


public class StudentInfoListItem {
    String name;
    int attendanceNumber;
    int balance;
    String classNumber; //반
    String school; //학교
    String job; //직업
    int creditScore; // 신용점수
    String email; //email


    public StudentInfoListItem(String name, /*String writer,*/ int attendanceNumber, int balance, String classNumber, String school, String job, int creditScore,String email) {
        this.name = name;
        this.attendanceNumber = attendanceNumber;
        this.balance = balance;
        this.classNumber = classNumber;
        this.school = school;
        this.job = job;
        this.creditScore = creditScore;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttendanceNumber() {
        return Integer.toString(attendanceNumber);
    }

    public void setAttendanceNumber(int attendanceNumber) {
        this.attendanceNumber = attendanceNumber;
    }

    public String getBalance() {
        return Integer.toString(balance);
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getClassNumber(){ return classNumber; }

    public String getschool(){ return school; }

    public String getJob(){ return job; }

    public String getcreditScore(){ return Integer.toString(creditScore); }

    public String getEmail() { return email;
    }
}