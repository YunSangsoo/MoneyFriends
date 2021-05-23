package com.example.moneyfriend;

import java.time.LocalDate;
import java.time.LocalTime;

public class Notice
{
    private String title;
    private String content;
    private String dateOfEnter;
    private String timeOfEnter;

    public Notice () {}

    public Notice(String title, String content, LocalDate dateOfEnter, LocalTime timeOfEnter)
    {
        this.title = title;
        this.content = content;
        this.dateOfEnter = dateOfEnter.toString();
        this.timeOfEnter = timeOfEnter.toString();
    }

    public String getTitle() { return title; }

    public String getContent() { return content; }

    public String getDateOfEnter() { return dateOfEnter; }

    public String getTimeOfEnter() { return timeOfEnter; }
}
