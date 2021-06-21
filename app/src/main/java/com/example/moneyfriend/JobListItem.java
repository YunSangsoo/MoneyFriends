
package com.example.moneyfriend;
//공지사항 리스트 뷰에 들어가는 리스트 아이템을 위한 클래스 파일입니다.


public class JobListItem {
    String jobName;
    int salary;


    public JobListItem(String jobName, /*String writer,*/ int salary) {
        this.jobName = jobName;
        this.salary = salary;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
