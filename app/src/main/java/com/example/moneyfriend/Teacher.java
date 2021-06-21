package com.example.moneyfriend;

public class Teacher {

    private String name;
    private int attendanceNumber; //출석번호
    private String classNumber; //반
    private String school; //학교
    private String job; //직업
    private int salary;// 급여
    private int creditScore; // 신용점수

    public Teacher(String name, String classNumber,String school){
        this.name = name;
        this.classNumber = classNumber;
        this.school = school;
    }
    public String getName() {
        return name;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public String getSchool() { return school; }
}
