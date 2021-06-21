package com.example.moneyfriend;
//공지사항 리스트 뷰에 들어가는 리스트 아이템을 위한 클래스 파일입니다.


public class StudentInfoListItem {
 String name;
 int attendanceNumber;
 int balance;


    public StudentInfoListItem(String name, /*String writer,*/ int attendanceNumber, int balance) {
        this.name = name;
        this.attendanceNumber = attendanceNumber;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttendanceNumber() {
        return attendanceNumber;
    }

    public void setAttendanceNumber(int attendanceNumber) {
        this.attendanceNumber = attendanceNumber;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}

