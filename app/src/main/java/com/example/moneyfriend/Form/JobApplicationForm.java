package com.example.moneyfriend.Form;

public class JobApplicationForm extends Form
{
    private String jobName; //신청하는 직업
    private String contentsOfCertificate; // 보유한 자격증들

    public JobApplicationForm(String studentName, int attendanceNumber, String title, String contents, String jobName, String contentsOfCertificate )
    {
        super(studentName, attendanceNumber, title, contents);
        this.jobName = jobName;
        this.contentsOfCertificate = contentsOfCertificate;
    }

    public String getJobName() { return jobName; }
    public String getContentsOfCertificate() { return contentsOfCertificate; }

    @Override
    public String toString() {
        return super.toString()+
        "jobName='" + jobName + '\'' +
                ", contentsOfCertificate='" + contentsOfCertificate + '\'';
    }


}
