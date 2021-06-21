package com.example.moneyfriend;

public class Student
{
    private static final int DEFAULT_CREDIT_SCORE= 0;
    private static final String DEFAULT_JOB= "무직";
    private static final int DEFAULT_SALARY= 0;

    private String name;
    private int attendanceNumber; //출석번호
    private String classNumber; //반
    private String school; //학교
    private String job; //직업
    private int salary;// 급여
    private int creditScore; // 신용점수



    //private String uid;//UID

    public Student(String name, int attendanceNumber, String classNumber, String school)
    {
        this.name = name;
        this.attendanceNumber = attendanceNumber;
        this.classNumber = classNumber;
        this.school = school;
      
        this.job = DEFAULT_JOB;
        this.salary = DEFAULT_SALARY; // 추후 프론트 단에서 설계예정.
        this.creditScore = DEFAULT_CREDIT_SCORE;
      
        //uid
        //this.uid = null;
    }

    public String getName() {
        return name;
    }

    public int getAttendanceNumber() {
        return attendanceNumber;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public String getSchool() { return school; }

    public String getJob() { return job; }

    public int getSalary() { return salary; }

    public void setSalary(int sal){
        this.salary = sal;
    }

    public int getCreditScore () {return  creditScore; }

    public void setCreditScore (int credits) {
        this.creditScore = credits;
    }

    public void setJob(String job) { this.job = job; }

    //public String getUid() { return uid;}


    //예시
    /*
    public int putuid(String puid){
        this.uid = puid;
        return 1;
    }*/
}
