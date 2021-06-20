package com.example.moneyfriend;
//공지사항 리스트 뷰에 들어가는 리스트 아이템을 위한 클래스 파일입니다.


public class ListItem {
 String title;
 String writer;
 String date;


    public ListItem(String title, /*String writer,*/ String date) {
        this.title = title;
        //this.writer = writer;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
/*
    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }
*/
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

