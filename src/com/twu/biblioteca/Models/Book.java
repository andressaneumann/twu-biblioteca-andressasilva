package com.twu.biblioteca.Models;

public class Book {

    private int id;
    private String title;
    private String author;
    private int yearReleased;
    private Boolean isAvailable;
    private Integer userIdOfBooker;

    public Book(int id, String title, String author, int yearReleased, Boolean isAvailable, Integer userIdOfBooker) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.yearReleased = yearReleased;
        this.isAvailable = isAvailable;
        this.userIdOfBooker = userIdOfBooker;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getYearReleased() {
        return yearReleased;
    }

    public void setYearReleased(int yearReleased) {
        this.yearReleased = yearReleased;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public Integer getUserIdOfBooker() {
        return userIdOfBooker;
    }

    public void setUserIdOfBooker(Integer userIdOfBooker) {
        this.userIdOfBooker = userIdOfBooker;
    }
}
