package com.example.moneyfriend.Form;

public class NewJobSuggestionForm extends Form
{
    private String reasonOfSuggestion; // 제안하는 이유
    private int salary; // 급여

    public NewJobSuggestionForm(String studentName, int attendanceNumber, String title, String contents, String reasonOfSuggestion, int salary)
    {
        super(studentName, attendanceNumber, title, contents);
        this.reasonOfSuggestion = reasonOfSuggestion;
        this.salary = salary;
    }

    public String getReasonOfSuggestion() {return reasonOfSuggestion;}
    public int getSalary() {return salary;}

    public String toString() {
        return super.toString()+
                "reasonOfSuggestion='" + reasonOfSuggestion + '\'' +
                ", salary='" + salary + '\'';
    }
}
