package com.example.moneyfriend;

public class Rule
{
    private int Chapter; // 장
    private int Article; // 조
    private int Paragraph; // 항
    private String content; // 내용

    public Rule(int chapter, int article, int paragraph, String content)
    {
        Chapter = chapter;
        Article = article;
        Paragraph = paragraph;
        this.content = content;
    }

    public int getChapter() {return Chapter;}
    public int getArticle() {return Article;}
    public int getParagraph() {return Paragraph;}
    public String getContent() {return content;}
}
