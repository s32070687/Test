package com.example.jason.test.Home.VO;

/**
 * Created by Jason on 2018/1/5.
 */

public class TestData {

    private String title;
    private String author;
    private String content;
    private String date;
    private String classify;

    public TestData(String title, String author, String content, String date, String classify) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.date = date;
        this.classify = classify;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

}
