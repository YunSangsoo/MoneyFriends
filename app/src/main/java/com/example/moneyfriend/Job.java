package com.example.moneyfriend;

public class Job
{
    private String name; //직업명
    private int salary; // 급여
    private int minimumCreditScore;

    public Job() { }

    public Job(String name, int salary, int minimumCreditScore)
    {
        this.name = name;
        this.salary = salary;
        this.minimumCreditScore = minimumCreditScore;
    }

    public String getName() { return name; }

    public int getSalary() { return salary; }

    public int getMinimumCreditScore() { return minimumCreditScore; }
}
